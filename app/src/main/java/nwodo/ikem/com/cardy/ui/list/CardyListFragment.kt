package nwodo.ikem.com.cardy.ui.list

import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import nwodo.ikem.com.cardy.R
import nwodo.ikem.com.cardy.databinding.CardyListFragmentBinding
import nwodo.ikem.com.cardy.ui.CardyAdapter
import nwodo.ikem.com.cardy.utils.CardyClickListener
import java.util.*
import javax.inject.Inject

class CardyListFragment : DaggerFragment(), CardyClickListener, TextToSpeech.OnInitListener
{

    companion object {
        fun newInstance() = CardyListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fragmentViewModel: CardyListFragmentViewModel

    lateinit var binding: CardyListFragmentBinding

    lateinit var textToSpeech: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cardy_list_fragment, container, false)

        binding.fab.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_cardyListFragment_to_addCardFragment)
        }

        return binding.root
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.GERMANY)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "TTS language not supported!", Toast.LENGTH_LONG)
                    .show()
            }
            textToSpeech.language = Locale.GERMANY
        }
    }

    private fun initializeTTS() {
        val checkIntent = Intent()
        checkIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        val MY_DATA_CHECK_CODE = 100
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            // success, create the TTS instance
            textToSpeech = TextToSpeech(context, this)
        } else {
            // missing data, install it

            val installIntent = Intent()
            installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
            startActivity(installIntent)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //TextToSpeech
        initializeTTS()

        fragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(CardyListFragmentViewModel::class.java)

        binding.lifecycleOwner = this
        val rvAdapter = CardyAdapter(this)
        binding.cardList.layoutManager = LinearLayoutManager(context)
        fragmentViewModel.loadCards.observe(viewLifecycleOwner,
            Observer { rvAdapter.submitList(it) })

        binding.cardList.adapter = rvAdapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Implement delete
                val position = viewHolder.adapterPosition
                fragmentViewModel.deleteCard(rvAdapter.getCardyAtPosition(position))
            }
        }).attachToRecyclerView(binding.cardList)

    }

    override fun onDestroyView() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroyView()
    }

    override fun onCardyLongClickListener(id: Int) {
        val action = CardyListFragmentDirections.actionCardyListFragmentToAddCardFragment(id)
        findNavController().navigate(action)
    }

    override fun onCardyClickListener(id: Int) {
        fragmentViewModel.loadSingleCard(id).observe(viewLifecycleOwner,
            Observer { cardEntity ->  textToSpeech.speak(cardEntity.german, TextToSpeech.QUEUE_ADD, null)
            })
    }

}


package nwodo.ikem.com.cardy.ui.add

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import nwodo.ikem.com.cardy.R
import nwodo.ikem.com.cardy.databinding.AddCardFragmentBinding
import nwodo.ikem.com.cardy.db.CardEntity
import javax.inject.Inject


class AddCardFragment : DaggerFragment() {

    companion object {
        fun newInstance() = AddCardFragment()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fragmentViewModel: AddCardFragmentViewModel

    private lateinit var binding: AddCardFragmentBinding

    val args: AddCardFragmentArgs by navArgs()
    private var newEntry = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.add_card_fragment, container, false)

        binding.saveUpdateButton.setOnClickListener {
            newEntry()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddCardFragmentViewModel::class.java)

        val cardyId = args.cardId
        if (cardyId > 0){
            newEntry = false
            binding.saveUpdateButton.text = getString(R.string.button_update)

            fragmentViewModel.loadSingleCard(cardyId).observe(viewLifecycleOwner,
                Observer{
                        cardEntity ->
                    run {
                        fillFields(cardEntity)
                    }
                })
        }

        binding.lifecycleOwner = this



    }

    private fun fillFields(cardEntity: CardEntity?) {
        binding.editTextGerman.setText(cardEntity?.german)
        binding.editTextEnglish.setText(cardEntity?.english)
    }


    private fun newEntry() {
        val english = binding.editTextEnglish.text.toString()
        val german = binding.editTextGerman.text.toString()

        if (english.isNotEmpty() && german.isNotEmpty()) {

            if (newEntry){
                fragmentViewModel.insertCard(CardEntity(german, english))
            }else{
                fragmentViewModel.updateCard(CardEntity(german, english))
            }


            dismissKeyboard(view?.windowToken)
            findNavController().navigate(R.id.cardyListFragment)
        }
    }

    private fun dismissKeyboard(windowToken: IBinder?) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}

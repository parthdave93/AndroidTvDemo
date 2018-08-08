package com.parthdave93.tvdemo.normal_tutorial.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v17.leanback.app.GuidedStepSupportFragment
import android.support.v17.leanback.widget.GuidanceStylist
import android.support.v17.leanback.widget.GuidedAction
import android.text.InputType
import android.util.Log
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.normal_tutorial.ui.MainActivity

/**
 * Created by Parth Dave on 26-07-2018.
 */
class LoginFragment : GuidedStepSupportFragment() {

    val EMAIL_ID: Long = 1
    val PASSWORD_ID: Long = 2

    override fun onProvideTheme(): Int {
        return R.style.Theme_Example_LeanbackWizard
    }

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Login",
                getString(R.string.app_name),
                "Breadcrumb", null)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {

        var action = GuidedAction.Builder(activity)
                .id(EMAIL_ID)
                .title(R.string.email_id)
                .editTitle("")
                .inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .editable(true)
                .hasNext(true)
                .description(R.string.type_email_id_here)
                .editDescription(R.string.type_email_id_here)
                .build()
        actions.add(action)

        action = GuidedAction.Builder(activity)
                .id(PASSWORD_ID)
                .editInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .title(getString(R.string.password))
                .editTitle("")
                .editable(true)
                .editDescription("Type your password here")
                .description("Type your password here")
                .build()
        actions.add(action)

    }


    override fun onCreateButtonActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        var okButton = GuidedAction.Builder(activity)
                .clickAction(GuidedAction.ACTION_ID_OK)
                .build()
        okButton.isEnabled = false
        actions.add(okButton)
    }

    override fun onGuidedActionEditedAndProceed(action: GuidedAction?): Long {
        Log.d("tag", "email: ${findActionById(EMAIL_ID).editTitle}, \n Password: ${findActionById(PASSWORD_ID).editTitle}")
        if (action?.id == EMAIL_ID) {
            action.editTitle = action.editTitle
            if (action.editTitle.length == 0)
                action.title = getString(R.string.email_id)
            else
                action.title = action.editTitle
            return GuidedAction.ACTION_ID_NEXT
        }
        /*if (action?.id == PASSWORD_ID) {
            action.editTitle = action.editTitle
            if (action.editTitle.length == 0)
                action.title = getString(R.string.password)
            else
                action.title = action.editTitle
        }*/
        if (findActionById(EMAIL_ID).editTitle.length > 0 && findActionById(PASSWORD_ID).editTitle.length > 0) {
            findButtonActionById(GuidedAction.ACTION_ID_OK).isEnabled = true
            notifyButtonActionChanged(findButtonActionPositionById(GuidedAction.ACTION_ID_OK))
        }
        return super.onGuidedActionEditedAndProceed(action)
    }


    override fun onGuidedActionClicked(action: GuidedAction?) {
        if(action?.id == GuidedAction.ACTION_ID_OK){
            var intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }
    }

}
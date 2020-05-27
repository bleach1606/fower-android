//package com.google.firebase.quickstart.fcm
//
//import android.content.Intent
//import com.google.android.gms.common.util.CollectionUtils.listOf
//
//class EntryChoiceActivity : BaseEntryChoiceActivity() {
//
//    override fun getChoices(): List<Choice> {
//        return kotlin.collections.listOf(
//                Choice(
//                        "Java",
//                        "Run the Firebase Cloud Messaging quickstart written in Java.",
//                        Intent(this, MainActivity::class.java)),
//                Choice(
//                        "Kotlin",
//                        "Run the Firebase Cloud Messaging written in Kotlin.",
//                        Intent(this, com.google.firebase.quickstart.fcm.kotlin.MainActivity::class.java))
//        )
//    }
//}
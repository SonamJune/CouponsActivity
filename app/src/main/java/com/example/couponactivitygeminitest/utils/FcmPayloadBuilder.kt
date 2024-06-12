package com.manash.purplle.utils

import android.os.Bundle
import org.json.JSONArray
import org.json.JSONObject

class FcmPayloadBuilder {

    companion object {

        //new keys for http v1 apis
        val EVENT_NAME = "pp_event_name"
        private const val NEW_CHANNEL_KEY = "pp_channel_key"
        const val NEW_CHANNEL_NAME = "pp_channel_name"
        private const val NEW_CHANNEL_IMPORTANCE = "pp_channel_importance"
        private const val NEW_CHANNEL_DESCRIPTION = "pp_channel_description"
        const val NEW_ADD_ACTION_TITLE_1 = "add_action_title1"
        private const val NEW_ADD_ACTION_TITLE_2 = "add_action_title2"
        private const val NEW_ADD_ACTION_TITLE_3 = "add_action_title3"
        private const val NEW_ADD_ACTION_TARGET_1 = "add_action_target1"
        private const val NEW_ADD_ACTION_TARGET_2 = "add_action_target2"
        private const val NEW_ADD_ACTION_TARGET_3 = "add_action_target3"
        const val NEW_CAROUSEL_IMG_1 = "pt_carousel_images1"
        private const val NEW_CAROUSEL_IMG_2 = "pt_carousel_images2"
        private const val NEW_CAROUSEL_IMG_3 = "pt_carousel_images3"

        //        private const val NEW_CAROUSEL_LINK_1 = "pt_carousel_link1"
//        private const val NEW_CAROUSEL_LINK_2 = "pt_carousel_link2"
//        private const val NEW_CAROUSEL_LINK_3 = "pt_carousel_link3"
        const val NEW_CAROUSEL_DEEP_LINK_1 = "pt_carousel_deeplink1"
        private const val NEW_CAROUSEL_DEEP_LINK_2 = "pt_carousel_deeplink2"
        private const val NEW_CAROUSEL_DEEP_LINK_3 = "pt_carousel_deeplink3"
        //

        //old keys in which we need to map the data
        private const val NAME = "name"
        private const val IMPORTANCE = "importance"
        private const val KEY = "key"
        private const val DESCRIPTION = "description"
        private const val ACTION_TITLE = "actionTitle"
        private const val ACTION_TARGET = "actionTarget"

        val PT_IMG1 = "pt_img1"
        val PT_IMG2 = "pt_img2"
        val PT_IMG3 = "pt_img3"

        val PT_DL1 = "pt_dl1"
        val PT_DL2 = "pt_dl2"
        val PT_DL3 = "pt_dl3"

        val PT_CAROUSEL_DEEPLINK_OLD_OBJECT = "pt_carousel_deeplink"
        val PT_CAROUSEL_IMAGES_OLD_OBJECT = "pt_carousel_images"

        fun getPpChannelString(bundle: Bundle?): String {
            val jsonObject = JSONObject()
            try {
                bundle?.let {
                    bundle.getStringIfExists(NEW_CHANNEL_NAME)?.let {
                        jsonObject.put(NAME, it)
                    }
                    bundle.getStringIfExists(NEW_CHANNEL_IMPORTANCE)?.let {
                        jsonObject.put(IMPORTANCE, it)
                    }
                    bundle.getStringIfExists(NEW_CHANNEL_KEY)?.let {
                        jsonObject.put(KEY, it)
                    }
                    bundle.getStringIfExists(NEW_CHANNEL_DESCRIPTION)?.let {
                        jsonObject.put(DESCRIPTION, it)
                    }
                }
                return jsonObject.toString()
            } catch (e: Exception) {
            }
            return jsonObject.toString()
        }

        fun getAddActionString(bundle: Bundle?): String {
            val jsonObject = JSONObject()
            var actionTitleArray = JSONArray()
            var actionTargetArray = JSONArray()

            try {
                bundle?.let { bundle ->
                    bundle.getStringIfExists(NEW_ADD_ACTION_TITLE_1)?.let {
                        actionTitleArray.put(it)
                    }
                    bundle.getStringIfExists(NEW_ADD_ACTION_TITLE_2)?.let {
                        actionTitleArray.put(it)
                    }
                    bundle.getStringIfExists(NEW_ADD_ACTION_TITLE_3)?.let {
                        actionTitleArray.put(it)
                    }
                    bundle.getStringIfExists(NEW_ADD_ACTION_TARGET_1)?.let {
                        actionTargetArray.put(it)
                    }
                    bundle.getStringIfExists(NEW_ADD_ACTION_TARGET_2)?.let {
                        actionTargetArray.put(it)
                    }
                    bundle.getStringIfExists(NEW_ADD_ACTION_TARGET_3)?.let {
                        actionTargetArray.put(it)
                    }
                }
            } catch (e: Exception) {

            }

            jsonObject.put(ACTION_TITLE, actionTitleArray)
            jsonObject.put(ACTION_TARGET, actionTargetArray)
            return jsonObject.toString()

        }


        fun getCarouselImagesString(bundle: Bundle?): String {
            val jsonObject = JSONObject()
            try { bundle?.let { bundle ->
                    bundle.getStringIfExists(NEW_CAROUSEL_IMG_1)?.let {
                        jsonObject.put(PT_IMG1, it)
                    }
                    bundle.getStringIfExists(NEW_CAROUSEL_IMG_2)?.let {
                        jsonObject.put(PT_IMG2, it)
                    }
                    bundle.getStringIfExists(NEW_CAROUSEL_IMG_3)?.let {
                        jsonObject.put(PT_IMG3, it)
                    }
                }
            } catch (e: Exception) {
            }
            return jsonObject.toString()
        }

        fun getCarouselDeepLinkString(bundle: Bundle?): String {
            val jsonObject = JSONObject()
            try {
                bundle?.let { bundle ->
                    bundle.getStringIfExists(NEW_CAROUSEL_DEEP_LINK_1)?.let {
                        jsonObject.put(PT_DL1, it)
                    }
                    bundle.getStringIfExists(NEW_CAROUSEL_DEEP_LINK_2)?.let {
                        jsonObject.put(PT_DL2, it)
                    }
                    bundle.getStringIfExists(NEW_CAROUSEL_DEEP_LINK_3)?.let {
                        jsonObject.put(PT_DL3, it)
                    }
                }
            } catch (e: Exception) {
            }
            return jsonObject.toString()
        }

    }
}

fun Bundle.getStringIfExists(key: String): String? {
    if (this.containsKey(key)) {
        return this.getString(key)
    }
    return null
}


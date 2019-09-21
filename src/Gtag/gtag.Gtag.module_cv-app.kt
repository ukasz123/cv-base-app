@file:JsQualifier("Gtag")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package Gtag

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface Gtag {
    @nativeInvoke
    operator fun invoke(command: String /* 'config' */, targetId: String, config: ControlParams? = definedExternally /* null */)
    @nativeInvoke
    operator fun invoke(command: String /* 'config' */, targetId: String, config: EventParams? = definedExternally /* null */)
    @nativeInvoke
    operator fun invoke(command: String /* 'config' */, targetId: String, config: CustomParams? = definedExternally /* null */)
    @nativeInvoke
    operator fun invoke(command: String /* 'set' */, config: CustomParams)
    @nativeInvoke
    operator fun invoke(command: String /* 'js' */, config: Date)
    @nativeInvoke
    operator fun invoke(command: String /* 'event' */, eventName: dynamic /* 'add_payment_info' | 'add_to_cart' | 'add_to_wishlist' | 'begin_checkout' | 'checkout_progress' | 'exception' | 'generate_lead' | 'login' | 'page_view' | 'purchase' | 'refund' | 'remove_from_cart' | 'screen_view' | 'search' | 'select_content' | 'set_checkout_option' | 'share' | 'sign_up' | 'timing_complete' | 'view_item' | 'view_item_list' | 'view_promotion' | 'view_search_results' | String */, eventParams: dynamic /* ControlParams | EventParams | CustomParams */ = definedExternally /* null */)
    @nativeInvoke
    operator fun invoke(command: String /* 'config' */, targetId: String)
}

external interface CustomParams {
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface ControlParams {
    var groups: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var send_to: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var event_callback: (() -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var event_timeout: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EventParams {
    var checkout_option: String?
        get() = definedExternally
        set(value) = definedExternally
    var checkout_step: Number?
        get() = definedExternally
        set(value) = definedExternally
    var content_id: String?
        get() = definedExternally
        set(value) = definedExternally
    var content_type: String?
        get() = definedExternally
        set(value) = definedExternally
    var coupon: String?
        get() = definedExternally
        set(value) = definedExternally
    var currency: String?
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var fatal: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var items: Array<Item>?
        get() = definedExternally
        set(value) = definedExternally
    var method: String?
        get() = definedExternally
        set(value) = definedExternally
    var number: String?
        get() = definedExternally
        set(value) = definedExternally
    var promotions: Array<Promotion>?
        get() = definedExternally
        set(value) = definedExternally
    var screen_name: String?
        get() = definedExternally
        set(value) = definedExternally
    var search_term: String?
        get() = definedExternally
        set(value) = definedExternally
    var shipping: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var tax: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var transaction_id: String?
        get() = definedExternally
        set(value) = definedExternally
    var value: Number?
        get() = definedExternally
        set(value) = definedExternally
    var event_label: String?
        get() = definedExternally
        set(value) = definedExternally
    var event_category: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Item {
    var brand: String?
        get() = definedExternally
        set(value) = definedExternally
    var category: String?
        get() = definedExternally
        set(value) = definedExternally
    var creative_name: String?
        get() = definedExternally
        set(value) = definedExternally
    var creative_slot: String?
        get() = definedExternally
        set(value) = definedExternally
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
    var location_id: String?
        get() = definedExternally
        set(value) = definedExternally
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var price: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var quantity: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Promotion {
    var creative_name: String?
        get() = definedExternally
        set(value) = definedExternally
    var creative_slot: String?
        get() = definedExternally
        set(value) = definedExternally
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
}
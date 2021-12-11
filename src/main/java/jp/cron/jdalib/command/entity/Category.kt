package jp.cron.jdalib.command.entity

import jp.cron.jdalib.command.Alias
import jp.cron.jdalib.command.Command
import jp.cron.jdalib.util.common.CommonUtil
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.lang.reflect.Method
import java.util.function.Consumer

abstract class Category {
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
    annotation class Declaration(val name: String, val prefix: String = "", val description: String = "")

    private val declaration = javaClass.getAnnotation(Declaration::class.java)

    var name: String
    var prefix: String
    var description: String

    val methods = mutableListOf<Method>()

    init {
        name = declaration.name
        prefix = declaration.prefix
        description = declaration.description
        init()
    }

    abstract fun init()

    open fun precall(e: MessageReceivedEvent) : Boolean {
        return true
    }

    fun call(e: MessageReceivedEvent) {
        if (e.message.contentRaw.startsWith(prefix)){
            methods.forEach { method ->
                val anno = method.getAnnotation(Command::class.java)
                val alias = method.getAnnotation(Alias::class.java)

                var willInvoke = false

                if (e.message.contentRaw.startsWith(prefix+anno.value)){
                    willInvoke = true
                }
                if (alias != null) {
                    for (a in alias.value) {
                        if (e.message.contentRaw.startsWith(prefix+a)){
                            println(true)
                            willInvoke = true
                        }
                    }
                }
                if (willInvoke && precall(e)){
                    method.invoke(this, e)
                }
            }
        }
    }

    fun registerMethodMap() {
        CommonUtil.getMethodsAnnotatedWith(
            this.javaClass,
            Command::class.java
        ).forEach(Consumer { m: Method? ->
            if (m != null) {
                methods.add(m)
            }
        })
    }
}
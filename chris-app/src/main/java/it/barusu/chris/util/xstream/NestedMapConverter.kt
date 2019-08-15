package it.barusu.chris.util.xstream

import com.google.common.base.Strings
import com.thoughtworks.xstream.converters.Converter
import com.thoughtworks.xstream.converters.MarshallingContext
import com.thoughtworks.xstream.converters.UnmarshallingContext
import com.thoughtworks.xstream.io.HierarchicalStreamReader
import com.thoughtworks.xstream.io.HierarchicalStreamWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

class NestedMapConverter(private val ordinalType: OrdinalType = OrdinalType.UNORDERED) : Converter {

    override fun marshal(source: Any?, writer: HierarchicalStreamWriter?, context: MarshallingContext?) {
        @Suppress("UNCHECKED_CAST")
        val map: AbstractMap<String, String> = source as AbstractMap<String, String>
        map.forEach {
            writer!!.startNode(it.key)
            writer.setValue(it.value)
            writer.endNode()
        }
    }

    override fun unmarshal(reader: HierarchicalStreamReader?, context: UnmarshallingContext?): Any {
        return nested(reader!!)
    }

    override fun canConvert(type: Class<*>?): Boolean {
        // this AbstractMap must be java.util.AbstractMap, not be kotlin.collections.AbstractMap
        return AbstractMap::class.java.isAssignableFrom(type!!)
    }

    private fun nested(reader: HierarchicalStreamReader): Map<Any, Any> {
        val map: MutableMap<Any, Any>
        when (ordinalType) {
            OrdinalType.ASCII -> map = TreeMap()
            OrdinalType.LINKED -> map = LinkedHashMap()
            else -> map = HashMap()
        }

        while (reader.hasMoreChildren()) {
            reader.moveDown()

            if (reader.hasMoreChildren()) {
                map[reader.nodeName] = nested(reader)
            } else {
                if (!Strings.isNullOrEmpty(reader.value)) {
                    map[reader.nodeName] = reader.value
                }
            }

            reader.moveUp()
        }

        return map

    }

}
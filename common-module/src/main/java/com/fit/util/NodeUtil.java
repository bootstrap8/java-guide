package com.fit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-12-23
 */
public class NodeUtil {

    final static String N_SB = "subNodes";
    final static String N_NA = "nodeAttrs";
    final static String N_TXT = "text";
    final static String N_NM = "name";

    /**
     * 创建一个元素
     *
     * @return
     */
    public static Map createElement() {
        return new HashMap();
    }

    /**
     * 创建元素
     *
     * @param name
     * @param text
     * @param attr
     * @param sns
     * @return
     */
    public static Map createElement(String name, String text, Map attr, List<Map> sns) {
        Map e = new HashMap();
        if (name != null) {
            e.put(N_NM, name);
        }
        if (text != null) {
            e.put(N_TXT, text);
        }
        if (attr != null) {
            e.put(N_NA, attr);
        }
        if (sns != null) {
            e.put(N_SB, sns);
        }
        return e;
    }

    /**
     * 增加一个属性，已经存在则覆盖
     *
     * @param element
     * @param k
     * @param v
     */
    public static void addAttr(Map element, String k, String v) {
        if (element.get(N_NA) != null) {
            ((Map) element.get(N_NA)).put(k, v);
        } else {
            Map attr = new HashMap();
            attr.put(k, v);
            element.put(N_NA, attr);
        }
    }

    /**
     * 增加一组属性
     *
     * @param element
     * @param attr
     */
    public static void addAttr(Map element, Map attr) {
        if (element.get(N_NA) != null) {
            ((Map) element.get(N_NA)).putAll(attr);
        } else {
            element.put(N_NA, attr);
        }
    }

    /**
     * 获取一个元素的属性
     *
     * @param element
     * @param k
     * @return
     */
    public static String getAttr(Map element, String k) {
        if (element.get(N_NA) == null) {
            return null;
        } else {
            Object a = ((Map) element.get(N_NA)).get(k);
            return a == null ? null : String.valueOf(a);
        }
    }

    /**
     * 获取该元素的所有属性
     *
     * @param element
     * @return
     */
    public static Map getAttr(Map element) {
        if (element.get(N_NA) == null) {
            return null;
        } else {
            return ((Map) element.get(N_NA));
        }
    }

    /**
     * 清除节点属性
     *
     * @param element
     */
    public static void clearAttr(Map element) {
        element.remove(N_NA);
    }

    /**
     * 设置该元素的名字
     *
     * @param element
     * @param name
     */
    public static void setName(Map element, String name) {
        element.put(N_NM, name);
    }

    /**
     * 获取该元素的名字
     *
     * @param element
     * @return
     */
    public static String getName(Map element) {
        return String.valueOf(element.get(N_NM));
    }

    /**
     * 设置元素的内部文本
     *
     * @param element
     * @param txt
     */
    public static void setText(Map element, String txt) {
        element.put(N_TXT, txt);
    }

    /**
     * 获取元素的内部文本
     *
     * @param element
     * @return
     */
    public static String getText(Map element) {
        return String.valueOf(element.get(N_TXT));
    }

    /**
     * 增加元素的子节点
     *
     * @param element
     * @param node
     */
    public static void addSubNode(Map element, Map node) {
        if (element.get(N_SB) != null) {
            List<Map> sl = (List<Map>) element.get(N_SB);
            sl.add(node);
        } else {
            List<Map> sl = new ArrayList<Map>();
            sl.add(node);
            element.put(N_SB, sl);
        }
    }

    /**
     * 增加一组子节点
     *
     * @param element
     * @param nodes
     */
    public static void AddSubNodes(Map element, List<Map> nodes) {
        if (element.get(N_SB) != null) {
            List<Map> sl = (List<Map>) element.get(N_SB);
            sl.addAll(nodes);
        } else {
            element.put(N_SB, nodes);
        }
    }

    /**
     * 清除节点子节点
     *
     * @param element
     */
    public static void clearSubNodes(Map element) {
        element.remove(N_SB);
    }

    /**
     * 获取元素的所有子节点
     *
     * @param element
     * @return
     */
    public static List<Map> getSubNode(Map element) {
        if (element.get(N_SB) != null) {
            return (List<Map>) element.get(N_SB);
        } else {
            return null;
        }
    }

    /**
     * 获取节点子的个数
     *
     * @param element
     * @return
     */
    public static int getSESize(Map element) {
        if (element.get(N_SB) == null) {
            return 0;
        } else {
            return ((List) element.get(N_SB)).size();
        }
    }

    /**
     * 获取节点属性的个数
     *
     * @param element
     * @return
     */
    public static int getEASize(Map element) {
        if (element.get(N_NA) == null) {
            return 0;
        } else {
            return ((Map) element.get(N_NA)).size();
        }
    }
}

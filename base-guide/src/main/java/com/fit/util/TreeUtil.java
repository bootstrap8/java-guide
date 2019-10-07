package com.fit.util;

import com.fit.bean.NodeEntity;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-06
 */
public class TreeUtil {

    public static final String ROOT_PID = "-1";

    /**
     * 使用递归方法建树
     *
     * @param nodeEntities
     * @return
     */
    public static List<NodeEntity> buildByRecursive(List<NodeEntity> nodeEntities) {
        List<NodeEntity> trees = new ArrayList<>();
        for (NodeEntity nodeEntity : nodeEntities) {
            if (ROOT_PID.equals(nodeEntity.getPid())) {
                trees.add(findChildren(nodeEntity, nodeEntities));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param nodeEntity
     * @param nodeEntitys
     * @return
     */
    public static NodeEntity findChildren(NodeEntity nodeEntity, List<NodeEntity> nodeEntitys) {
        nodeEntity.setChildren(Lists.newArrayList());
        for (NodeEntity it : nodeEntitys) {
            if (nodeEntity.getId().equals(it.getPid())) {
                if (nodeEntity.getChildren() == null) {
                    nodeEntity.setChildren(Lists.newArrayList());
                }
                nodeEntity.getChildren().add(findChildren(it, nodeEntitys));
            }
        }
        return nodeEntity;
    }

}

package com.fit.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-07
 */
@Data
public class NodeEntity {
    private String id;
    private String label;
    private String pid;
    private List<NodeEntity> children;
}

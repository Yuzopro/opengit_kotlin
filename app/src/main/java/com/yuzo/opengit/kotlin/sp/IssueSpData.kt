package com.yuzo.opengit.kotlin.sp

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
var filterSp: String by SpDelegate.preference("issue_filter", "assigned")

var stateSp by SpDelegate.preference("issue_state", "open")

var sortSp by SpDelegate.preference("issue_sort", "created")

var directionSp by SpDelegate.preference("issue_direction", "asc")
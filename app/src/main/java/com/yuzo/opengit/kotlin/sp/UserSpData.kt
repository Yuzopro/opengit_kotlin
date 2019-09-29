package com.yuzo.opengit.kotlin.sp

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
var tokenSp: String by SpDelegate.preference("access_token", "")

var accountSp by SpDelegate.preference("account", "")

var passwordSp by SpDelegate.preference("password", "")

var userSp by SpDelegate.preference("user", "")
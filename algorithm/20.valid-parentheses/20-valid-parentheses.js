/*
 * @lc app=leetcode id=20 lang=javascript
 *
 * [20] Valid Parentheses
 */

/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function(s) {
    if(!s) {
        return true
    }
    var stack = []
    for(let c of s) {
        switch(c) {
            case "{":
            case "[":
            case "(":
                stack.push(c);
                break;
            case ")":
                if(stack[stack.length-1]==="(") {
                    stack.pop();
                } else {
                    return false;
                }
                break;
            case "}":
                if(stack[stack.length-1]==="{") {
                    stack.pop();
                } else {
                    return false;
                }
                break;
            case "]":
                if(stack[stack.length-1]==="[") {
                    stack.pop();
                } else {
                    return false;
                }
                break;
            default:
                return false;
        }
    }
    return stack.length==0;
};



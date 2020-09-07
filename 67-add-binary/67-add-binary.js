// @lc code=start
/**
 * @param {string} a
 * @param {string} b
 * @return {string}
 */
var addBinary = function(a, b) {
    let carry = 0;
    let result = "";
    let i = a.length-1;
    let j = b.length-1;
    for(;i>=0 && j>=0;i--,j--) {
        const tmp = Number(a[i]) + Number(b[j]) + carry;
        if(tmp > 1) {
            result = (tmp-2) + result;
            carry = 1;
        } else {
            result = tmp + result;
            carry = 0;
        }
    }
    for(;i>=0;i--) {
        const tmp = Number(a[i]) + carry;
        if(tmp > 1) {
            result = (tmp-2) + result;
            carry = 1;
        } else {
            result = tmp + result;
            carry = 0;
        }

    }

    for(;j>=0;j--) {
        const tmp = Number(b[j]) + carry;
        if(tmp > 1) {
            result = (tmp-2) + result;
            carry = 1;
        } else {
            result = tmp + result;
            carry = 0;
        }
        
    }
    if(carry > 0) {
        result = carry + result;
    }
    return result;
};


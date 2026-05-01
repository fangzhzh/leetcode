/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function(haystack, needle) {
    const BASE = 1000000;
    if(!needle) {
        return 0;
    }

    const m = needle.length;
    // get powerMax
    let powerMax = 1;
    for(let c of needle) {
        powerMax = (powerMax*31) % BASE;
    }
    // get hash for needle
    let target = 0;
    for(let c of needle) {
        target = (target*31 + c.charCodeAt(0)) % BASE;
        }
    // get abc+d
    let source = 0;
    for(let i = 0; i < haystack.length; i++) {
        source = (source*31 + haystack[i].charCodeAt(0)) % BASE;
        if(i < m -1) {
            continue;
        }

        if(i>=m) {
            source = (source - haystack[i-m].charCodeAt(0)*powerMax) % BASE;
            if(source < 0) {
                source += BASE;
            }
        }
        if(source == target) {
            if(haystack.substring(i-m+1, i+1) === needle) {
                return i-m+1;
            }
        }
    }
    return -1;

};

var letterCombinations = function(digits) {
    if(!digits) {
        return [];
    }
    const map = {
        2: "abc",
        3: "def",
        4: "ghi",
        5: "jkl",
        6: "mno",
        7: "pqrs",
        8: "tuv",
        9: "wxyz",
    };
    const strList = []
    for(let char of digits) {
        strList.push(map[char])
    }
    let results=[];
    dp(results, digits.length, "", strList);
    return results;
};

function dp(results, len, cur, strList) {
    if(cur.length === len) {
        results.push(cur);
        return
    }
    const first = strList[0];
    for(let char of first) {
        dp(results, len, cur+char, strList.slice(1));
    }
}

/**
 * Accepted
25/25 cases passed (72 ms)
Your runtime beats 79.97 % of javascript submissions
Your memory usage beats 29.8 % of javascript submissions (36.8 MB)
 */

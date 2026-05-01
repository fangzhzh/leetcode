// @lc code=start
/**
 * Initialize your data structure here.
 */
var WordDictionary = function() {
    this.head = {
        children: {},
        isWord: false
    };
};

/**
 * Adds a word into the data structure. 
 * @param {string} word
 * @return {void}
 */
WordDictionary.prototype.addWord = function(word) {
    var cur = this.head;
    for(let i = 0; i < word.length; i++) {
        if(cur.children[word[i]] == null) {
            let newNode = {
                children: {}
            }
            cur.children[word[i]] = newNode;
        }
        cur = cur.children[word[i]];
        cur.isWord = (cur.isWord || (i == word.length-1));
    }
};

/**
 * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. 
 * @param {string} word
 * @return {boolean}
 */
WordDictionary.prototype.search = function(word) {
    let queue = [this.head];
    for(let i = 0; i < word.length; i++) {
        const queueLenth = queue.length;
        for(let j = 0; j < queueLenth; j++) {
            const cur = queue.shift();
            if(word[i] === ".") {
                for(let child in cur.children) {
                    queue.push(cur.children[child]);
                }
            } else {
                const node = cur.children[word[i]];
                if(node) {
                    queue.push(node);
                }
            }
        }
    }
    for(let node of queue) {
        if(node.isWord) {
            return true;
        }
    }
    return false;
};


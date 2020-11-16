/**
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1

Input:
"filetestbuffer"
read(6)
read(5)
read(4)
read(3)
read(2)
read(1)
read(10)
Output:
6, buf = "filete"
5, buf = "stbuf"
3, buf = "fer"
0, buf = ""
0, buf = ""
0, buf = ""
0, buf = ""
Example 2

Input:
"abcdef"
read(1)
read(5)
Output:
1, buf = "a"
5, buf = "bcdef"
Notice
The read function may be called multiple times.
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */


      




/**
 * 
 * 
    "filetestbuffer"
    read(6)
    read(5)
    read(4)
    read(3)
    read(2)
    read(1)
    read(10)

 * cacheSize bufSize shift
 * // read 6
 *  0           4       0
 *  2           4       2
 * // read 5
 *  0           2       0
 *  1           5       1
 * // ....
 *  
 * 
 * 
 * cache size repsents how many chars in cache
 * shift means how many has already read from cache
 *  */      
public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    
    char[]cache = new char[4];
    int cacheSize = 0;
    int shift = 0;
    public int read(char[] buf, int n) {
        // Write your code here
        int charInBuf = 0;
        boolean eof = false;
        
        while(charInBuf < n && !eof) {
            if(cacheSize == 0) {
                cacheSize = read4(cache);
                eof = cacheSize < 4;
            }
            int cpSize = Math.min(cacheSize, n - charInBuf);
            for(int i = 0; i < cpSize; i++) {
                buf[charInBuf+i] = cache[shift+i];
            }
            charInBuf += cpSize;
            cacheSize -= cpSize;
            shift = (shift+cpSize) % 4;
        }
        return charInBuf;
    }
}

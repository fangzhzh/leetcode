class Solution {
public:
    void reverseWords(string &s) {
 if(s.length() <= 0){
        return ;
    }
    std::stringstream ss (s);
    std::vector<std::string> result ;
    std::string tmp;
    while(ss.good()){
        ss>>tmp;
        if (!ss.fail()){
	        result.push_back(tmp);
        }
    }

    std::string rtStr = "";
    while(!result.empty()) {
        rtStr += result.back() + " ";
        result.pop_back();
    }

    trim(rtStr);
    s = rtStr;
}
void trim(std::string& s){
    while(s.find_first_of(" \n\r\t") == 0){
        s.erase(0);
    }
    s.erase(s.find_last_not_of(" \n\r\t")+1);
}
};

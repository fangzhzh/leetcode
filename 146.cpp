#include <iostream>     // std::cout
#include <string>
#include <algorithm>    // std::reverse
#include <vector>       // std::vector
#include <unordered_map>
using namespace std;
class LRUCache;
class Node{
friend class LRUCache;
	private:
		int key;
		int value;
		Node* prev;
		Node* next;
	public:
		Node(int key, int value){
			this->key = key;
			this->value = value;
			prev = NULL;
			next = NULL;
		}
		void deleteNode(){
			if(next != NULL){
				next->prev = prev;
			}
			if(prev != NULL){
				prev->next = next;
			}
			delete this;
		}
		void addAfter(Node *prev){
		}
};

class LRUCache{
	Node *head;
	Node *tail;
	Node *dummy;
	unordered_map<int, Node*> map;
	int capacity;

public:
    LRUCache(int capacity) {
       this->capacity = capacity; 
	   dummy = new Node(0,0);
	   head = tail = dummy;
	   map[0]=dummy;
    }
    
    int get(int key) {
		unordered_map<int, Node*>::const_iterator it = map.find(key);
		if(it == map.end()){
			return -1;
		} 
		set(key, it->second->value);
		return it->second->value;
    }
    
    void set(int key, int value) {
		unordered_map<int, Node*>::const_iterator it = map.find(key);
		if(it != map.end()){
			it->second->value = value;
			if( head != tail){
				if(head == it->second){
					head = it->second->next;	
				}
				if(tail != it->second){
					moveToTail(it->second);
				}
			} else{

			}
		} else {
			if(map.size() >= capacity){
				if(head != tail){
					Node* curHead = head;
					head = head->next;
					map.erase(curHead->key);
					curHead->deleteNode();
				} else {
					map.erase(head->key);
					head->key = key;
					head->value = value;
					map[key] = head;
					return;
				}
			}

			Node* node = new Node(key, value);
			map[key] = node;
			addToTail(node);
		}
    }

	void moveToTail(Node* node){
		if(node->next != NULL){
			node->next->prev = node->prev;
		}
		if(node->prev != NULL){
			node->prev->next = node->next;
		}
		addToTail(node);
	}

	void addToTail(Node* node){
		tail->next = node;
		node->prev = tail;
		node->next = NULL;
		tail = node;
	}
	 
	int size(){
		return map.size();
	}

	void print(bool b=false){
		if(!b){
			return;
		}
		Node* curHead = head;
		cout << "capicity " << capacity << " size " << size() << " values: ";
		int i=0;
		while(curHead!=NULL && i < 5){
			i++;
			cout << " key " << curHead->key << " value " << curHead->value;
			curHead = curHead->next;
		}
		cout << endl;
	}
};
int main(){
	LRUCache cache(2);
	cache.set(2,1);
	cache.print();
	cache.set(1,1);
	cout << cache.get(2) << endl;
	cache.print();
	cache.print();
	cache.set(4,1);
	cout << cache.get(1) << endl;
	cout << cache.get(2) << endl;
	cache.print(true);

	return 0;

	LRUCache cache1(3);
	cache1.set(1,1);
	cache1.print();
	cache1.set(2,2);
	cache1.print();
	cache1.set(3,3);
	cache1.print();
	cache1.set(4,4);
	cache1.print();
	cache1.set(5,5);
	cache1.print();
	cache1.set(7,7);
	cache1.print();
	cache1.set(4,4);
	cache1.print();
	cache1.set(3,3);
	cache1.print();
	cache1.set(2,2);
	cache1.print();

	return 0;
}

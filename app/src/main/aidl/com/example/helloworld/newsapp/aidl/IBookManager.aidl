// IBookManager.aidl
package com.example.helloworld.newsapp.aidl;

// Declare any non-default types here with import statements
import com.example.helloworld.newsapp.aidl.Book;
import com.example.helloworld.newsapp.aidl.IOnNewBookArrivedListener;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}

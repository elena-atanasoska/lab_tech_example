import React, { useEffect, useState } from 'react';

const BookList = () => {
        console.log(process.env.REACT_APP_BACKEND_HOST);

        const [books, setBooks] = useState([]);
      
        useEffect(() => {
          fetch(`${process.env.REACT_APP_BACKEND_HOST}/api/books/all`)
       //   .then(response => console.log(response.json()));
            .then(response => response.json())
            .then(data => {
              setBooks(data);
            })
        }
        , []);
      
        const bookList = books.map(book => {
          return <tr key={book.id}>
            <td>{book.bookName}</td>
            <td>{book.author}</td>
            <td>{book.price}</td>
            <td>{book.isInStock.toString()}</td>
          </tr>
        });
      
      
        return (
          <div className="App">
            <header className="App-header">
              <div className="App-intro">
                <h2>Book List</h2>
                <table className="mt-4">
                <thead>
                <tr>
                  <th>Title</th>
                  <th>Author</th>
                  <th>Price</th>
                  <th>In Stock</th>
                </tr>
                </thead>
                <tbody>
                {bookList}
                </tbody>
              </table>
              </div>
            </header>
          </div>
        );
    }

export default BookList;
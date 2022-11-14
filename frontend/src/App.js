import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Book from './components/pages/Book';
import BookDetails from './components/pages/bookDetails';
import CreateBook from './components/pages/createBook';
import DeleteStudent from './components/pages/deleteStudent';
import DeleteStudentBorrow from './components/pages/deleteStudentBorrow';
import GetDeny from './components/pages/getDeny';
import Home from './components/pages/Home';
import Pricing from './components/pages/Pricing';
import Student from './components/pages/student';
import StudentBorrow from './components/pages/studentBorrow';


function App() {
  return (
    <div className="App">
      {/* <Header/> */}
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' exact element={<Home />} />
          <Route path='/students' element={<Student />} />
          <Route path='/books' element={<Book />} />
          <Route path='/createbook' element={<CreateBook />} />
          <Route path='/pricing' element={<Pricing />} />
          <Route path='/studentBorrow' element={<StudentBorrow />} />
          <Route path='/bookDetails' element={<BookDetails />} />
          <Route path='/deleteStudent' element={<DeleteStudent />} />
          <Route path='/deleteStudentBorrow' element={<DeleteStudentBorrow />} />
          <Route path='/deniedStudents' element={<GetDeny />} />


        </Routes>
      </Router>
    </div>
  );
}

export default App;

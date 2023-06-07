import axios from "axios";

class StudentService {
    getStudent(page){
        return axios.get("http://localhost:8080/student/list?page="+page);
    }
    deleteStudent(id){
        axios.delete("http://localhost:8080/student/delete/"+id);
    }
    createStudent(name,dateOfBirth,point){
        let student ={
            name : name,
            dateOfBirth: dateOfBirth,
            point: point
        }
        return axios.post("http://localhost:8080/student/create",student);
    }

    findById(id){
        return axios.get("http://localhost:8080/student/detail/"+id);
    }
    updateStudent(id,name,dateOfBirth,point){
        let student ={
            id: id,
            name : name,
            dateOfBirth: dateOfBirth,
            point: point
        }
        return axios.put("http://localhost:8080/student/edit",student);
    }
}

export default new StudentService();
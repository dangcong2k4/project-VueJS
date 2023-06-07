<template>
    <div class="container">
        <h1>Danh sách học sinh</h1>
        <router-link :to="{name: 'create'}">
            <button class="btn btn-primary">thêm mới</button>
        </router-link>
        <div style="height: 400px">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Điểm</th>
                    <th scope="col">Xử Lý</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(student , index) in students" :key="index">
                    <th>{{index+1}}</th>
                    <td>{{student.name}}</td>
                    <td>{{student.dateOfBirth}}</td>
                    <td>{{student.point}}</td>
                    <td>
                        <button class="btn btn-danger" @click="deleteStudent(student.id,student.name)">Xóa</button>
                        <label>--</label>
                        <router-link :to="{name: 'update', params: {id: student.id}}">
                            <button class="btn btn-primary">Sửa</button>
                        </router-link>

                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <button :disabled="page===0" class="btn btn-primary" style="display: inline" @click="previousPage()">trang
                trước
            </button>
            <button :disabled="test" class="btn btn-outline-primary" style="display: inline;border-color: cyan">trang
                {{this.page+1}}
            </button>
            <button :disabled="students.length<5" class="btn btn-primary" style="display: inline" @click="nextPage()">
                trang sau
            </button>
        </div>
    </div>
</template>

<script>
    import StudentService from "@/service/StudentService";
    import Swal from 'sweetalert2';
    export default {
        name: "ListStudent",
        data() {
            return {
                test: true,
                page: 0,
                students: []
            };
        },
        methods: {
            nextPage() {
                this.page += 1;
                this.getStudent(this.page);
            },
            previousPage() {
                this.page -= 1;
                this.getStudent(this.page);
            },

            getStudent(page) {
                // alert("ghhg")
                StudentService.getStudent(page).then(response => {
                    this.students = response.data.content;
                    console.log(this.students)
                }).catch(error => {
                    console.error(error);
                });
            },

            deleteStudent(id,name) {
                Swal.fire({
                    title: 'Bạn có muốn xóa?',
                    text: 'Học sinh: ' + name,
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#d33',
                    cancelButtonColor: '#BBBBBB',
                    confirmButtonText: 'Có',
                    cancelButtonText: 'Không'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.$request.delete("http://localhost:8080/student/delete/"+id).then(response =>{
                                if (response.data.success===undefined){
                                    this.getStudent(0);
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Xóa thành công học sinh: ' + name,
                                        showConfirmButton: false,
                                        timer: 2000
                                    });
                                }
                            });
                    }
                });
            }
        },
        created() {
            this.getStudent();
        }
    }
</script>

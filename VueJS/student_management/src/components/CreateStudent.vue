<template>
    <div>
        <router-link :to="{name: 'list'}">
            <button class="btn btn-primary">quay lại trang list</button>
        </router-link>
        <h1>trang thêm mới</h1>
        <div class="container" style="width: 500px">
            <form @submit.prevent="saveStudent()">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Tên học sinh</label>
                    <div style="height: 50px">
                        <input type="text" class="form-control" id="exampleInputEmail1"
                               v-model="student.name" v-bind:class="{'is-invalid': errors.name}" @blur="validate()">
                        <p class="invalid-feedback">{{errors.name}}</p>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Ngày sinh</label>
                    <div style="height: 50px">
                        <input type="date" class="form-control" id="exampleInputPassword1"
                               v-model="student.dateOfBirth" v-bind:class="{'is-invalid': errors.dateOfBirth}" @blur="validate()">
                        <p class="invalid-feedback">{{errors.dateOfBirth}}</p>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="hahah" class="form-label">Điểm</label>
                    <div style="height: 50px">
                        <input type="text" class="form-control" id="hahah"
                               v-model="student.point" v-bind:class="{'is-invalid': errors.point}" @blur="validate()">
                        <p class="invalid-feedback">{{errors.point}}</p>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
                <router-link :to="{name: 'list'}">
                    <button type="reset" class="btn btn-danger">Cancel</button>
                </router-link>
            </form>
        </div>


    </div>

</template>

<script>
    import StudentService from "@/service/StudentService";
    import Swal from 'sweetalert2';
    export default {
        name: "CreateStudent",
        data(){
            return {
                errors: {
                    name:'',
                    dateOfBirth:'',
                    point:''
                },
                student: {
                    name:'',
                    dateOfBirth:'',
                    point:''
                }
            }
        },
        methods:{
            validate(){
                let isValid = true;
              this.errors = {
                  name:'',
                  dateOfBirth:'',
                  point:''
              }
              if (!this.student.name){
                    this.errors.name = 'tên không được để trống'
                  isValid = false;
              }
              if (!this.student.dateOfBirth){
                    this.errors.dateOfBirth = 'ngày sinh không được để trống'
                  isValid = false;
              }

              if (!this.student.point){
                    this.errors.point = 'điểm không được để trống'
                  isValid = false;
              } else if (!this.checkNumber(this.student.point)){
                    this.errors.point = 'điểm phải là ký tự số'
                  isValid = false;
              } else if (!this.checkPoint(this.student.point)){
                  this.errors.point = 'điểm tối đa là 10 và điểm tối thiểu là 0'
                  isValid = false;
              }
                return isValid;

            },
            checkNumber(value){
              return /^\d*$/.test(value)
            },
            checkPoint(value){
              if (value<=10 && value >=0){
                  return true;
              }else {
                  return false;
              }
            },

            saveStudent() {
                if (this.validate()){
                    StudentService.createStudent(this.student.name,this.student.dateOfBirth,this.student.point).then(response =>{
                        console.log(response.data.success)
                            this.$router.push({name: 'list'})
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Thêm học sinh thành công',
                                showConfirmButton: false,
                                timer: 2000
                            });
                    })
                }
            }
        }
    }
</script>

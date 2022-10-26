<template>
  <div class="hr-list">
    <div class="hr-content">
      <div class="hr-title">Quản lý nhân sự</div>
      <br>
      <div class="row">
        <div class="col-4" style="margin-left: 35px;">
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
              <el-input v-model="text" placeholder="find by name or code or department"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="warning" @click="onFind()">Tìm kiếm</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="col-2" style="margin-left: 332px;">
          <el-popover 
                placement="left"
                width="600"
                trigger="click">
                <h3 style="font-size: 30px;" class="hr-detail__title"><Strong>Thêm mới nhân viên</Strong></h3> 
                <form id="form-staff">
                  <div class="mb-3">
                    <label class="form-label"><strong>Mã nhân viên</strong></label>
                    <input v-model="staff.code" type="text" id="code" name="code"
                     class="form-control" required
                      placeholder="Mời bạn nhập mã nhân viên"/>
                  </div>
                  <div v-show="showValidateNullCode" style="color: red;">Mã nhân viên không được để trống</div>
                  <div v-show="showValidateCode" style="color: red;">Mã nhân viên đã tồn tại</div>

                  <div class="mb-3">
                    <label class="form-label"><Strong>Họ tên nhân viên</Strong></label>
                    <input v-model="staff.name" type="text" id="name" name="name"
                    class="form-control" required @click="checkCode()"
                    placeholder="Mời bạn nhập họ tên nhân viên">
                  </div>
                  <div v-show="showValidateNullName" style="color: red;">Họ tên nhân viên không được để trống</div>

                  <div class="mb-3">
                    <label class="form-label"><Strong>Email</Strong></label>
                    <input name="email" v-model="staff.email" type="email" required
                    class="form-control" placeholder="Mời bạn nhập email nhân viên" @click="checkCode()">
                  </div>
                  <div v-show="showValidateEmail" style="color: red;">Email đã tồn tại</div>
                  <div v-show="showValidateNullEmail" style="color: red;">Email nhân viên không được để trống</div>

                  <div class="mb-3">
                    <label class="form-label"><Strong>Ngày sinh</Strong></label>
                    <input v-model="staff.date" id="date" name="date" required @click="checkCode()"
                    type="text" class="form-control" 
                    placeholder="MM/DD/yyyy">
                  </div>
                  <div v-show="showValidateNullDate" style="color: red;">Ngày sinh nhân viên không được để trống</div>

                  <div class="mb-3">
                    <label class="form-label"><Strong>Số tiền hỗ trợ phúc lợi</Strong></label>
                    <input v-model="staff.welfareMoney" @click="checkCode()" required id="welfareMoney" name="welfareMoney"
                    type="number" 
                     class="form-control" 
                     placeholder="Mời bạn nhập số tiền nhân viên">
                  </div>
                  <div v-show="showValidateNullMoney" style="color: red;">Tiền hỗ trợ nhân viên không được để trống</div>

                  <div class="mb-3">
                    <label class="form-label"><Strong>Phòng ban</Strong> </label>
                    <select v-model="staff.department" placeholder="Chọn phòng ban" @click="checkCode()"
                    class="form-control"  name="department" id="department">
                      <option v-for="x in departments" :value="x.id" :key="x.id">
                        {{x.name}}
                      </option>
                    </select>
                    <!-- <el-select v-model="staff.department" placeholder="Chọn phòng ban" name="department" id="department">
                      <el-option
                        v-for="x in departments"
                        :key="x.id"
                        :label="x.name"
                        :value="x.id">
                      </el-option>
                    </el-select> -->
                  </div>
                  <div v-show="showValidateNullDepartment" style="color: red;">Mã nhân viên không được để trống</div>

                  <div style="text-align: center;">
                    <button @click.prevent ="create" class="btn btn-success">Thêm</button>
                    <button style="margin-left: 20px;" @click.prevent ="reset" class="btn btn-warning">Reset</button>
                  </div>
                </form>
                <el-button slot="reference" class="btn btn-danger">Thêm mới</el-button>
          </el-popover>
        </div>
        <div class="col-6"></div>
      </div>
      <div class="hr-info">
        <div class="hr-table">
          <div class="hr-selected">
          </div>
          <div class="hr-table__content">
            <table>
              <thead>
                <tr>
                  <th width="90px">TT</th>
                  <th width="70px">STT</th>
                  <th width="250px">Họ và tên</th>
                  <th width="200px">Mã nhân viên</th>
                  <th width="300px">Phòng ban</th>
                  <th width="90px">info</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, index) in staffs.content" :key="index">
                  <td>
                    <!-- <span class="icon-delete" @click="deleteStaff(item.id)">
                      <i class="fa fa-trash" aria-hidden="true"></i>
                    </span> -->
                    <span>
                      <el-button @click="deleteStaff(item.id,index)" type="danger" icon="el-icon-delete" circle></el-button>
                    </span>
                  </td>
                  <td>{{ index + 1 }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.code }}</td>
                  <td>{{ item.department.name }}</td>
                  <td>
                    <span class="icon-edit" @click="showInfo(item)">
                      <i class="fa fa-circle-info"></i>
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
            <el-pagination style="text-align: right;"
                background
                layout="prev, pager, next"
                :page-count="count"
                :page-size="pageSize"
                :page-sizes="pageSizes"
                @current-change="handlePageChange"
                hide-on-single-page>
            </el-pagination>
          </div>
  
          <!-- modal them moi -->
          <!-- <div class="hr-function" >
            <el-button round class="hr-detail__button" @click="showDialog"
            style="background-color: white;" >Thêm mới</el-button
            >
          </div> -->
        </div>
        <transition name="slideLeft" v-if="isShow">
          <div class="hr-table__detail" ref="detail">
            <div class="hr-detail__header">
              <span class="hr-detail__title">Thông tin nhân viên</span>
              <span class="hr-detail__close" @click="isShow = false">
                <i class="fa fa-times" aria-hidden="true"></i>
              </span>
            </div>
            <div class="hr-detail__body">
              <ul>
                <li>
                  <span class="hr-detail__label"
                    >Mã nhân viên: <Strong>{{ form.code }}</Strong></span>
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Họ tên: <Strong>{{ form.name }}</Strong></span>
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Ngày sinh: <Strong>{{ form.date }}</Strong></span>
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Email: <Strong>{{ form.email }}</Strong></span>
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Phòng ban: <Strong>{{ form.department.name }}</Strong></span>
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Tổng tiền hỗ trợ phúc lợi: <Strong>{{ form.welfareMoney }} </Strong></span>
                </li>
              
              </ul>
            </div>
            <div class="hr-detail__footer">
              <el-popover 
                    placement="left"
                    width="600"
                    trigger="click">
                    <h3 style="font-size: 30px;" class="hr-detail__title"><Strong>Sửa thông tin nhân viên</Strong></h3> 
                    <form id="form-staff2">
                      <div class="mb-3">
                        <label class="form-label"><strong>Mã nhân viên</strong></label>
                        <input v-model="staff.code" type="text" id="code" name="code"
                        class="form-control" required
                          placeholder="Mời bạn nhập mã nhân viên"/>
                      </div>

                      <div class="mb-3">
                        <label class="form-label"><Strong>Họ tên nhân viên</Strong></label>
                        <input v-model="staff.name" type="text" id="name" name="name"
                        class="form-control" required 
                        placeholder="Mời bạn nhập họ tên nhân viên">
                      </div>

                      <div class="mb-3">
                        <label class="form-label"><Strong>Email</Strong></label>
                        <input name="email" v-model="staff.email" type="email" required
                        class="form-control" placeholder="Mời bạn nhập email nhân viên" >
                      </div>

                      <div class="mb-3">
                        <label class="form-label"><Strong>Ngày sinh</Strong></label>
                        <input v-model="staff.date" id="date" name="date" required
                        type="text" class="form-control" 
                        placeholder="dd/mm/yyyy">
                      </div>
          
                      <div class="mb-3">
                        <label class="form-label"><Strong>Số tiền hỗ trợ phúc lợi</Strong></label>
                        <input v-model="staff.welfareMoney" required id="welfareMoney" name="welfareMoney"
                        type="number"
                        class="form-control" 
                        placeholder="Mời bạn nhập số tiền nhân viên">
                      </div>
                      <div class="mb-3">
                        <label class="form-label"><Strong>Phòng ban</Strong> </label>
                        <select v-model="staff.department" placeholder="Chọn phòng ban"
                          class="form-control"  name="department" id="department">
                          <option v-for="x in departments" :value="x.id" :key="x.id">
                            {{x.name}}
                          </option>
                        </select>
                      </div>
                      <div style="text-align: center;">
                        <button @click.prevent="update(form.id)" class="btn btn-success">Sửa</button>
                        <button style="margin-left: 20px;" @click.prevent ="reset" class="btn btn-warning">Reset</button>
                      </div>
                    </form>
                    <el-button @click="getEdit(form.id)"
                      style="text-align: center; font: 1em sans-serif; margin-right: 20px;"
                    slot="reference" class="btn btn-danger">Sửa thông tin nhân viên</el-button>
              </el-popover>
              <el-popover
                placement="left"
                width="350"
                trigger="click">
                <div style="font-size: 30px;" class="hr-detail__title"><Strong>Phúc lợi đang hưởng</Strong></div> 
                <span><Strong style="font-size: 20px;"><i class="el-icon-success"></i> Phúc lợi cá nhân hóa</Strong></span>
                <ul v-for="x in welfares" :key="x.id" style="list-style-type:none ;">
                    <li><Strong>Phúc lợi</Strong> {{x.welfare.name}} <div></div> <Strong>Giá trị</Strong> {{x.welfare.price}} VNĐ <i class="el-icon-money"></i></li>
                </ul>
                <br>
                <span><Strong style="font-size: 20px;"> <i class="el-icon-circle-plus"></i>  Phúc lợi chung</Strong></span>
                <ul v-for="x in gerenalWelfares" :key="x.id" style="list-style-type:none ;">
                    <li><Strong>Phúc lợi</Strong> {{x.name}} <div></div> <Strong>Giá trị</Strong> {{x.price}} VNĐ <i class="el-icon-money"></i></li>
                </ul>
                
                <el-button @click="handleShow(form.id)" slot="reference" 
                style="text-align: center; font: 1em sans-serif;" class="btn btn-danger">
                Kiểm tra phúc lợi
                </el-button>
              </el-popover>
            </div>
          </div>
        </transition>      
      </div>
      <div class="hr-image"></div>
    </div>
  </div>
</template>

<script>
import moment from "moment"
import StaffService from "@/service/hrService";
export default {
  name: "HrList",
  data () {
    return {
      staffs: [],
      welfares: [],
      gerenalWelfares: [],
      departments: [],
      text: '',
      date: '',
      staffId: '',
      content: '',
      count: 0,
      pageSize: 10,
      pageSizes: [2, 4, 6],
      multipleSelection: [],
      staff: {
        code: '',
        name: '',
        welfareMoney: '',
        date: '',
        email: '',
        department: ''
      },
      code: [],
      email: [],
      price: [],
      value: "",
      isShow: false,
      showDialogAdd: false,
      status: true,
      showValidateCode:false,
      showValidateEmail:false,
      showValidateNullCode: false,
      showValidateNullName: false,
      showValidateNullEmail: false,
      showValidateNullDate: false,
      showValidateNullMoney: false,
      showValidateNullDepartment: false
    };
  },
  // created(){
  //   this.staff.date = this.formatDate(this.staff.date)
  // },
  methods: {
    getRequestParams (page, pageSize) {
      let params = {}
      if (page) {
        params[ 'page' ] = page - 1
      }
      if (pageSize) {
        params['size'] = pageSize
      }
      return params
    },
    handlePageChange (value) {
      this.page = value
      console.log(this.page)
      this.retrieveStaff()
    },
    handleSelectionChange (val) {
      let ids = []
      val.forEach(v => {
        ids.push(v.id)
      })
      this.multipleSelection = [...ids]
      console.log(ids)
      console.log(this.multipleSelection)
    },
  
    showDialog() {
      this.showDialogAdd = true;
      this.isShow = false;
    },
    showInfo(item) {
      this.isShow = false;
      setTimeout(() => {     
        this.form = item;
        this.form.date = this.formatDate(item.date)
        this.form.welfareMoney = item.welfareMoney.toLocaleString('vi', {style : 'currency', currency : 'VND'});
        this.isShow = true;
        console.log(item.welfareMoney)
      }, 100);
    },
    formatDate(value){
      if (value) {
        return moment(String(value)).format('DD/MM/yyyy')
      }
    },
    formatPrice(str) {
      return str.split('').reverse().reduce((prev, next, index) => {
        return ((index % 3) ? next : (next + ',')) + prev
    })},
    reset() {
      this.staff.code = ''
      this.staff.date = ''
      this.staff.department = ''
      this.staff.email = ''
      this.staff.name = ''
      this.staff.welfareMoney = ''
    },
    retrieveStaff () {
      const params = this.getRequestParams(
        this.page,
        this.pageSize
      )
      StaffService.getAll(params)
      .then(response => {
        this.staffs = response.data
        // this.staffs.content = 
        this.count = response.data.totalPages
        this.itemCount = response.data.totalElements
      })
      .catch(e => {
          console.log(e)
        })
    },
    onFind() {
      console.log(this.text)
      StaffService.getFind(this.text)
        .then(response => {
          this.staffs = response.data
          this.count = response.data.totalPages
        this.itemCount = response.data.totalElements
        console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
 
    getDepartments () {
      StaffService.getDepartments()
      .then(response => {
        this.departments = response.data
        console.log(response.data)
      })
    },
    handleShow (id) {
      this.staffId = id
      console.log(id)
      StaffService.getWelfare(id)
        .then(response => {
          this.welfares = response.data
          this.price = this.welfares.welfare.price
          console.log(this.price)
          console.log(response.data)
        })
      StaffService.getGeneralWelfare()
        .then(response => {
          this.gerenalWelfares = response.data
          console.log(response.data)
        })  
      this.status = false
    },

    getEdit (id) {
      StaffService.getStaff(id)
        .then(response => {
          this.staff = response.data
          this.staff.date = this.formatDate(response.data.date)
          console.log(id)
        })
    },
    deleteStaff (id, index) {
      this.$confirm('Bạn có chắc là xóa nhân viên này không. Continue?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          StaffService.deleteStaff(id)
          this.staffs.content.splice(index,1);
          this.$message({
            type: 'success',
            message: 'Delete completed'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Delete canceled'
          });          
        });
    },
    
    codeExists(text) {
      return this.code.some(function(el) {
        return el.text === text;
      }); 
    },

    checkCode(){
      console.log(this.staff.code)
      if(this.code.includes(this.staff.code)){
        this.showValidateCode = true
      }else{
        this.showValidateCode = false
      }
      if(this.email.includes(this.staff.email)){
        this.showValidateEmail = true
      }else{
        this.showValidateEmail = false
      }
      if(this.staff.code===""){
        this.showValidateNullCode = true
      }else{
        this.showValidateNullCode = false
      }
      if(this.staff.name===""){
        this.showValidateNullName = true
      }else{
        this.showValidateNullName = false
      }
      if(this.staff.email===""){
        this.showValidateNullEmail = true
      }else{
        this.showValidateNullEmail = false
      }
      if(this.staff.date===""){
        this.showValidateNullDate = true
      }else{
        this.showValidateNullDate = false
      }
      if(this.staff.department===""){
        this.showValidateNullDepartment = true
      }else{
        this.showValidateNullDepartment = false
      }
    },

    listCode() {
      StaffService.getCode()
      .then(response => {
          this.code = response.data
        })
        .catch(e => {
          console.log(e)
        })
    },
    listEmail() {
      StaffService.getEmail()
      .then(response => {
          this.email = response.data
        })
        .catch(e => {
          console.log(e)
        })
    },
    create () {
      let form = document.querySelector('#form-staff')
      let formdata = new FormData(form);
      StaffService.createStaff(formdata)
        .then(response => {
          console.log(response.data)
          this.retrieveStaff()
          this.$notify({
            title: 'Success',
            message: 'Thêm mới nhân viên thành công',
            type: 'success'
          });
          this.reset()
        }).catch(e => {
          console.log(e)
          this.$notify({
            title: 'Warning',
            message: 'Bạn nhập sai thông tin',
            type: 'warning',
          });
        })
    },
    update (id) {
      let form = document.querySelector('#form-staff2')
      let formdata = new FormData(form);
      
      StaffService.updateStaff(id, formdata)
        .then(response => {
          console.log(response.data)
          this.retrieveStaff()
          this.$notify({
            title: 'Success',
            message: 'Cập nhật thông tin nhân viên thành công',
            type: 'success'
          });
          this.reset()
        }).catch(e => {
          console.log(e)
          this.$notify({
            title: 'Warning',
            message: 'Bạn nhập sai thông tin',
            type: 'warning',
          });
        })
    },
  },
 
  mounted () {
    // this.staff.date = this.formatDate(this.staff.date)
    this.retrieveStaff()
    this.getDepartments()
    this.listCode()
    this.listEmail()
  }
};
</script>

<style>
.hr-image {
  background-image: url("@/assets/image/profiledata.svg");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  width: 600px;
  height: 600px;
  position: absolute;
  bottom: 0;
  right: 0;
}
.hr-list {
  background: linear-gradient(90deg, #e4c9ac 0%, rgba(255, 255, 255, 0) 100%),
    #e3c1d3;
  width: 100%;
  height: 100%;
}
.hr-info {
  display: flex;
}
.hr-title {
  text-align: center;
  font-size: 34px;
  font-weight: 600;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background: rgba(255, 255, 255, 0.13);
  padding: 6px 0px;
}
.icon-delete {
  cursor: pointer;
}
.icon-delete i {
  color: #f00;
}
.icon-edit {
  cursor: pointer;
}
.icon-edit i {
  color: #409eff;
}
.hr-table {
  width: fit-content;
  margin-left: 35px;
  margin-right: 35px;
  z-index: 2;
}
.hr-table__content table {
  width: 100%;
  border-collapse: collapse;
}
.hr-table__content table tr {
  background: #f2e7ddf8;
  text-align: center;
}
.hr-table__content table tr th {
  border-right: 1px solid #e4c9ac;
  padding: 14px;
}
.hr-table__content table tr td {
  padding: 8px;
  height: 30px;
  line-height: 30px;
}
.hr-table__content table thead tr {
  background: #fdf9f8;
}
.hr-table__content table thead th {
  font-weight: 600;
  font-size: 14px;
}
.hr-table__content table tbody tr {
  border-bottom: 1px solid #94806af8;
}
.hr-table__content table tbody tr:hover {
  background: #fdf9f8;
}
.hr-table__content table tbody tr:last-child {
  border-bottom: none;
}
.hr-info {
  display: flex;
}
.hr-table__detail {
  width: 200px;
  height: 500px;
  background: #fff;
  border-radius: 30px;
  flex-direction: column;
  flex: 0.7;
  z-index: 2;
}
.hr-detail__header {
  padding: 24px;
  font-size: 24px;
  font-weight: 700;
  display: flex;
  justify-content: space-between;
}
.hr-detail__title {
  color: #000;
  border-bottom: 2px solid #f004;
}
.hr-detail__close {
  color: #f00;
  cursor: pointer;
}
.hr-detail__body {
  flex: 1;
}
.hr-detail__body ul {
  margin: 0;
}
.hr-detail__body ul li {
  padding: 8px 0px;
}
.hr-detail__footer {
  display: flex;
  justify-content: flex-end;
  padding: 28px 28px;
}
.hr-detail__button {
  color: #f00 !important;
  font-size: 14px !important;
  font-weight: 600 !important;
}
.hr-detail__button:hover {
  background-color: rgba(255, 0, 0, 0.1) !important;
  border-color: rgba(255, 0, 0, 0.1) !important;
}
.hr-detail__button:focus {
  background-color: rgba(255, 0, 0, 0.2) !important;
  border-color: rgba(255, 0, 0, 0.2) !important;
}
.hr-detail__button:active {
  background-color: rgba(255, 0, 0, 0.3) !important;
  border-color: rgba(255, 0, 0, 0.3) !important;
}
.hr-function {
  margin-top: 40px;
}
.el-select {
  width: 100%;
}
.el-input__inner {
  border-radius: 0px !important;
  font-family: "Poppins,sans-serif";
  background-blend-mode: saturation;
  font-style: normal;
  font-weight: 600;
  font-size: 20;
}
.el-form-item__label {
  color: #f00 !important;
  font-size: 20px;
  font-weight: 600;
  line-height: 31px;
  letter-spacing: 0em;
  text-align: left;
  line-height: 0px;
  margin-bottom: 6px;
}
.el-form-item__content input {
  border-radius: 8px !important;
  box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.15);
  border: 1px solid #23282b8f;
}
.el-dialog__title {
  margin-left: 56px;
  font-size: 25px;
  font-weight: 700;
  line-height: 35px;
  letter-spacing: 0em;
  text-align: left;
}
.el-dialog__headerbtn i {
  font-size: 24px;
  font-weight: 700;
  color: #f00 !important;
}
</style>
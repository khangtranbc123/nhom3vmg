<template>
  <div class="hr-list">
    <div class="hr-content">
      <div class="hr-title">Quản lý nhân sự</div>
      <div class="hr-info">
        <div class="hr-table">
          <div class="hr-selected">
            <el-select v-model="value" placeholder="Select">
              <!-- <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option> -->
            </el-select>
          </div>
          <div class="hr-table__content">
            <table>
              <thead>
                <tr>
                  <th width="5%">TT</th>
                  <th width="5%">STT</th>
                  <th width="200px">Họ và tên</th>
                  <th width="150px">Mã nhân viên</th>
                  <th width="300px">Email</th>
                  <th width="150px">Phòng ban</th>
                  <th width="5%"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, index) in hrList" :key="index">
                  <td>
                    <span class="icon-delete" @click="deleteStaff(item.id)">
                      <i class="fa fa-trash" aria-hidden="true"></i>
                    </span>
                  </td>
                  <td>{{ index + 1 }}</td>
                  <td>{{ item.hoTen }}</td>
                  <td>{{ item.maNV }}</td>
                  <td>{{ item.email }}</td>
                  <td>{{ item.phongBan.ten }}</td>
                  <td>
                    <span class="icon-edit" @click="showInfo(item)">
                      <i class="fa fa-circle-info"></i>
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="hr-function" >
            <el-button round class="hr-detail__button" @click="showDialog"
            style="background-color: white;" >Thêm mới</el-button
            >
          </div>
        </div>
        <transition name="slideLeft" v-if="isShow">
          <div class="hr-table__detail" ref="detail">
            <div class="hr-detail__header">
              <span class="hr-detail__title">Thông tin cá nhân</span>
              <span class="hr-detail__close" @click="isShow = false">
                <i class="fa fa-times" aria-hidden="true"></i>
              </span>
            </div>
            <div class="hr-detail__body">
              <ul>
                <li>
                  <span class="hr-detail__label"
                    >Họ và tên: {{ form.hoTen }}</span
                  >
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Email: {{ form.email }}</span
                  >
                </li>
                <li>
                  <span class="hr-detail__label"
                    >Mã nhân viên: {{ form.maNV }}</span
                  >
</li>
                <!--                  <li>-->
                <!--                    <span class="hr-detail__label"-->
                <!--                      >Giới tính: {{ hrInfo.gender }}</span-->
                <!--                    >-->
                <!--                  </li>-->
                <!-- <li>
                  <span class="hr-detail__label"
                    >Ngày sinh: {{ form.ngaySinh }}</span
                  >
                </li> -->
                <!-- <li>
                  <span class="hr-detail__label"
                    >Địa chỉ thường trú: {{ hrInfo.address }}</span
                  >
                </li> -->
                <!-- <li>
                  <span class="hr-detail__label"
                    >Tình trạng hôn nhân: {{ hrInfo.maritalStatus }}</span
                  >
                </li> -->
                <!-- <li>
                  <span class="hr-detail__label"
                    >Vị trí: {{ hrInfo.position }}</span
                  >
                </li> -->
                <li>
                  <span class="hr-detail__label"
                    >Phòng ban: {{ form.phongBan.ten }}</span
                  >
                </li>
                <!-- <li>
                  <span class="hr-detail__label"
                    >Sở thích: {{ hrInfo.hobby }}</span
                  >
                </li> -->
              </ul>
            </div>
            <div class="hr-detail__footer">
              <el-button
                round
                class="hr-detail__button"
                @click="showDialogAdd = true"
                >Sửa thông tin
              </el-button>
              <el-button round class="hr-detail__button"
                >Xem phúc lợi của tôi
              </el-button>
            </div>
          </div>
        </transition>
      </div>
      <div class="hr-image"></div>
    </div>
    <el-dialog
      title="Thông tin cá nhân"
      :visible.sync="showDialogAdd"
      width="900px"
      label-width="100px"
      top="5vh"
    >
      <!-- :before-close="handleClose" -->
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="Tên">
          <el-input v-model="form.hoTen"></el-input>
        </el-form-item>
      <el-form-item label="Ngày sinh">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="Chọn ngày sinh" v-model="form.ngaySinh" style="width: 100%;"></el-date-picker>
      </el-col>
      </el-form-item>
      <el-form-item label="Code">
        <el-input type="text" v-model="form.maNV"></el-input>
      </el-form-item>
      <el-form-item label="Phòng">
        <el-select v-model="form.phongBan.ten" placeholder="Chọn phòng">
                <el-option
                  v-for="item in departments"
                  :key="item.id"
                  :value="item.id"
                >
                {{item.ten}}
                </el-option>
              </el-select>
</el-form-item>
      <el-form-item label="Email">
        <el-input type="text" v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="Trạng Thái">
      <el-select v-model="form.trangThai" placeholder="Chọn trạng thái">
        <el-option label="1.Đang làm" value="1"></el-option>
        <el-option label="2.Đã nghỉ" value="2"></el-option>
      </el-select>
      </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addStaff(form)">Cập nhật</el-button>
      </el-form-item>
    </el-form>
    </el-dialog>
  </div>
</template>

<script>
import StaffApi from "@/service/hrService";
let staffApi = new StaffApi();
export default {
  name: "HrList",
  data() {
    return {
      departments: [
        {
          id: 1,
          ten:"Phòng Phát Triển Phần Mềm",
          TrangThai:1
        },
        {
          id: 2,
          ten:"Phòng Nhân Sự",
          TrangThai:1
        },
        {
          id: 3,
          ten:"Phòng Kinh Doanh",
          TrangThai:1
        }
      ],
      value: "",
      isShow: false,
      showDialogAdd: false,
      form: { 
        hoTen: '',
        maNV: '',
        ngaySinh: new Date(),
        trangThai: '',
        phongBan: {},
        email: ''
      },
      positions: [
        {
          id:1,
          ten: "Nhân Viên",
        },
        {
          id: 2,
          ten: "Lãnh Đạo",
        }
      ],
      hrList: [  
      ],
    };
  },
  methods: {
    showDialog() {
      // this.hrInfo = {
      //     code: 'NV004',
      // }
      // this.hrList.push(this.hrInfo);
      this.showDialogAdd = true;
      // this.form = {};
      this.isShow = false;
    },
    showInfo(item) {
      this.isShow = false;
      setTimeout(() => {
        this.form = item;
        this.isShow = true;
      }, 100);
    },
    addStaff(form){
      const qs = require("qs");
      // this.form.hoTen = '';
      staffApi.createStaff(qs.stringify(form)).then((res) => {
        console.log(res.data);
        staffApi.getAllStaff().then((res) => {
            // self.isLoaded = true;
            this.list = res.data;
          });
        });
      this.showDialogAdd = false;
      location.reload()
    },
    deleteStaff(item) {
      staffApi.deleteStaff(item).then((res) => {
        if (res.status == 200) {
          const index = this.hrList.findIndex((a) => a.id == item.id);
          this.hrList.splice(index, 1);
        }
      });
    }
  },
  //13221
  created() {

    staffApi.getAllStaff().then((res) => {
      // self.isLoaded = true;
    this.hrList = res.data;
      console.log(res.data)
     
    });
  },
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
  font-family: "Poppins";
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
  width: 500px;
  height: 500px;
  background: #fff;
  border-radius: 30px;
  display: flex;
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
  border-bottom: 2px solid #f004a;
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
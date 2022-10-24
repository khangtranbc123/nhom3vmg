<template>
  <div class="pl-body">
    <div class="pl-content">
      <div class="pl-title">DANH SÁCH PHÚC LỢI</div>
      <div class="pl-ele">
        <div class="pl-table">
          <!-- <div class="pl-select">
              <el-select v-model="value" placeholder="Chọn Phòng Ban">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div> -->
          <div class="pl-table__content">
            <form id="form" label-width="120px">
              <table>
                <thead>
                  <tr>
                    <th>Chọn</th>
                    <th>Tên phúc lợi</th>
                    <th>Mô tả</th>
                    <th>Thành Tiền(VNĐ)</th>
                    <!-- <th>Thao tác</th> -->
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item in list" :key="item.id">
                    <!-- <td>{{ index + 1 }}</td> -->
                    <td>
                      <input
                        type="checkbox"
                        :value="item"
                        v-model="selected"
                        :ref="item.id"
                        :disabled="disableHandler(item)"
                      />
                    </td>

                    <td>{{ item.name }}</td>
                    <td>{{ item.text }}</td>
                    <td>{{ item.price }}</td>

                    <!-- <td>
                        <span class="icon-edit" @click="showEditForm(item)">
                          <i class="fa fa-edit"></i> </span
                        >&nbsp;<span
                          class="icon-delete"
                          @click="showDeleteDialog(item.id)"
                        >
                          <i class="fa fa-trash"></i>
                        </span>
                      </td> -->
                  </tr>

                  {{
                    4000 - totalMoney
                  }}
                  <tr v-if="isShowAdd">
                    <td></td>
                    <td>
                      <input
                        type="text"
                        v-model="edit.ten"
                        id="ten"
                        name="ten"
                        placeholder="Nhập tên phúc lợi"
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        v-model="edit.moTa"
                        id="moTa"
                        name="moTa"
                        placeholder="Nhập mô tả"
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        v-model="edit.giaTri"
                        id="giaTri"
                        name="giaTri"
                        placeholder="Nhập tổng tiền"
                      />
                    </td>
                    <td>
                      <el-button round class="pl-button__detail" @click="Add()"
                        >Xác nhận</el-button
                      >
                      <el-button
                        round
                        class="pl-button__detail"
                        @click="Cancel()"
                        >Hủy</el-button
                      >
                    </td>
                  </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>

        <div class="pl-button">
          <el-button round class="pl-button__detail" @click="registerWelfares"
            >Thêm mới</el-button
          >
        </div>
      </div>
    </div>
    <el-dialog
      title="Thông tin phúc lợi"
      :visible.sync="isShowEdit"
      width="600px"
      label-width="100px"
      top="5vh"
    >
      <el-form
        :model="edit"
        ref="edit"
        label-width="120px"
        label-position="top"
      >
        <el-row>
          <el-col :span="6">
            <el-form-item label="Tên phúc lợi" prop="name">
              <el-input v-model="edit.ten"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="2">
            <el-form-item label="Thành tiền" prop="address">
              <el-input type="number" v-model="edit.giaTri"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="Mô tả" prop="code">
              <el-input type="textarea" v-model="edit.moTa"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button class="hr-detail__button" round @click="editWel(edit)"
          >Cập nhật
        </el-button>
      </span>
    </el-dialog>
    <pre>{{userName}}</pre>
  </div>
</template>
  
  <script>
/* eslint-disable */
import WelfareApi from "@/service/phucLoiService";
let welfareApi = new WelfareApi();
export default {
  name: "PhucLoiList",
  data() {
    return {
      checkedNames: [],
      options: [
        {
          value: "PTPM",
          lable: "Phong Phat Trien PM",
        },
        {
          value: "NS",
          lable: "Phong Nhan Su",
        },
      ],
      selected: [],
      value: "",
      isShowEdit: false,
      isShowDialog: false,
      isShowAdd: false,
      edit: {
        id: "",
        ten: "",
        moTa: "",
        giaTri: "",
      },
      list: [],
      userName: "",
    };
  },
  computed: {
    totalMoney() {
      if (this.selected.length > 0) {
        return this.selected
          .map((x) => x.price)
          .reduce((total, x) => total + x);
      } else {
        return 0;
      }
    },
  },
  watch: {
    selected() {},
  },
  methods: {
    disableHandler(item) {
      return (
        this.totalMoney + item.price > 3000 && !this.selected.includes(item)
      );
    },
    showAddForm() {
      this.edit = {};
      this.isShowAdd = true;
    },
    Add() {
      let isValidate = true;
      if (!(this.edit.ten && this.edit.moTa && this.edit.giaTri)) {
        this.$alert(
          "Thông tin đầu vào không hợp lệ vui lòng nhập đủ thông tin!",
          "Thông tin không hợp lệ",
          {
            confirmButtonText: "OK",
            callback: () => {},
          }
        );
        isValidate = false;
      }
      if (isValidate) {
        let form = document.querySelector("#form");
        console.log(form);
        welfareApi.createWelfare(form).then((res) => {
          welfareApi.getAllWelfare().then((res) => {
            // self.isLoaded = true;
            this.list = res.data;
          });
        });
        this.isShowAdd = false;
      }
    },
    Cancel() {
      this.isShowAdd = false;
    },
    showEditForm(item) {
      this.isShowEdit = false;
      setTimeout(() => {
        this.edit = item;
        this.isShowEdit = true;
      }, 100);
    },
    showDeleteDialog(item) {
      welfareApi.deleteWelfare(item).then((res) => {
        if (res.status == 200) {
          const index = this.list.findIndex((a) => a.id == item.id);
          this.list.splice(index, 1);
        }
      });
    },
    // editWel(edit) {
    //   let isValidate = true;
    //   if (!(edit.ten && edit.moTa && edit.giaTri)) {
    //     this.$alert(
    //       "Thông tin đầu vào không hợp lệ vui lòng nhập đủ thông tin!",
    //       "Thông tin không hợp lệ",
    //       {
    //         confirmButtonText: "OK",
    //         callback: () => {},
    //       }
    //     );
    //     isValidate = false;
    //   }
    //   if (isValidate) {
    //     const qs = require("qs");
    //     welfareApi.updateWelfare(edit.id, qs.stringify(edit));
    //     this.isShowEdit = false;
    //   }
    // },
    async registerWelfares() {
      let object = {
        id: 1,
        list: this.selected,
      };
      const res = await welfareApi.registerWelfare(object);
      console.log((res.status = 201 ? "Thêm thành công" : "Thêm thất bại"));
    },
  },
  created() {
    console.log("created");
    // const self = this;

    welfareApi.getAllWelfare().then((res) => {
      // self.isLoaded = true;
      this.list = res.data;
      console.log(res.data);
      console.log(this.list);
    });
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.userName = JSON.parse(localStorage.getItem("user")).userName;
    }
  },
};
</script>l
  
  <style>
.pl-title {
  text-align: center;
  font-size: 34px;
  font-weight: 600;
  font-family: "Poppins";
  background: rgba(255, 255, 255, 0.13);
  padding: 6px 0px;
}
.pl-body {
  background: linear-gradient(90deg, #e4c9ac 0%, rgba(255, 255, 255, 0) 100%),
    #e3c1d3;
  width: 100%;
  height: 100%;
}
.pl-table {
  text-align: center;
  margin-left: 200px;
  margin-right: 200px;
  z-index: 2;
}
.pl-table__content table {
  width: 100%;
  border-collapse: collapse;
}
.pl-table__content table tr {
  background: #f2e7ddf8;
}
.pl-table__content table tr th {
  border-right: 1px solid #e4c9ac;
  padding: 14px;
}
.pl-table__content table tr td {
  padding: 20px;
  line-height: 30px;
  height: 30px;
}
.pl-table__content table thead tr {
  background-color: #fdf9f8;
}
.pl-table__content table thead th {
  font-size: 14px;
  font-weight: 600;
}
.pl-table__content table tbody tr {
  border-bottom: 1px solid #e4c9ac;
}
.pl-table__content table tr td {
  border-right: 1px solid #e4c9ac;
}
.pl-table__content table tbody tr:hover {
  background-color: pink;
}
.icon-delete {
  font-size: 20px;
  color: coral;
  cursor: pointer;
}
.icon-edit {
  font-size: 20px;
  color: greenyellow;
  cursor: pointer;
}
/* input, */
textarea {
  outline: none;
  padding: 20px;
  width: 60%;
  background: transparent;
  border: none;
  border-bottom: 1px solid #000;
}
/* input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  } */
/* [placeholder]:focus::-webkit-input-placeholder {
    transition: text-indent 0.4s 0.4s ease;
    text-indent: -100%;
    opacity: 1;
  } */
.pl-button {
  margin-left: 200px;
  margin-top: 30px;
}
.pl-button__detail {
  color: #f00 !important;
  font-size: 14px !important;
  font-weight: 600 !important;
}
.pl-button__detail:hover {
  background-color: rgba(255, 0, 0, 0.1) !important;
  border-color: rgba(255, 0, 0, 0.1) !important;
}
.pl-button__detail:focus {
  background-color: rgba(255, 0, 0, 0.2) !important;
  border-color: rgba(255, 0, 0, 0.2) !important;
}
.pl-button__detail:active {
  background-color: rgba(255, 0, 0, 0.3) !important;
  border-color: rgba(255, 0, 0, 0.3) !important;
}
.el-form-item__label {
  color: #f00 !important;
  font-size: 22px;
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

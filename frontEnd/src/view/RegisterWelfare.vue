<template>
    <div class="hr-list">
        <div class="hr-content">
            <div class="hr-title">Xét duyệt đăng ký phúc lợi</div>
            <br>
            <br>
            <div class="hr-info">
                <div class="hr-table">
                    <div class="hr-selected">
                    </div>
                    <div class="hr-table__content">
                    <table>
                        <thead>
                        <tr>
                            
                            <th width="5%">STT</th>
                            <th width="200px">Email</th>
                            <th width="200px">Tên phúc lợi</th>
                            <th width="300px">Giá trị</th>
                            <th width="200px">TT</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(item, index) in registers" :key="index">
                            <td>{{ index + 1 }}</td>
                            <td>{{ item.email }}</td>
                            <td>{{ item.welfare.name }}</td>
                            <td>{{ item.welfare.price }} vnd</td>
                            <td>
                            <el-button @click="handleSucces(item.id)"  type="success" icon="el-icon-check" circle></el-button>   
                     
                            <el-button @click="handleDelete(item.id)" type="danger" icon="el-icon-delete" circle></el-button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
 import StaffService from "@/service/hrService";
 export default {
 name: "RegisterWelfareList",
    data () {
      return {
        registers: [],
        registerId: '',
        value: "",
        isShow: false,
        showDialogAdd: false,
        status: true,
      };
    },
    methods: {
      showDialog() {
        this.showDialogAdd = true;
        this.isShow = false;
      },
      showInfo(item) {
        this.isShow = false;
        setTimeout(() => {
          this.form = item;
          this.isShow = true;
        }, 100);
      },
      getAll () {
        StaffService.getRegisterWelfare()
        .then(response => {
          this.registers = response.data
          console.log(response.data)
        })
      },
      handleDelete(id) {
        if(confirm('Bạn muốn hủy đăng ký này?')){
            this.registerId = id
            console.log(id)
            StaffService.DeleteRegisterWelfare(id)
            location.reload(true);
        }
      },
      handleSucces(id) {
        StaffService.SuccessRegisterWelfare(id);
        this.getAll();
        const h = this.$createElement;
        this.$notify({
          title: 'Title',
          message: h('i', { style: 'color: teal' }, 'successful')
        });
        
      }
    },
   created() {
    this.getAll()
   },
    mounted () {
      this.getAll();
    }
  };
</script>
  
  <style>
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
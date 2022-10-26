<template>
  <div class="pl-main">
    <div class="pl-container">
      <div class="pl-content">
        <div id="title">Danh Sách Phúc Lợi Của Tôi</div>
        <div label-width="120px" class="pl-table__content">
          <table>
            <thead>
              <tr>
                <th>STT</th>
                <th>Loại Phúc Lợi</th>
                <th>Tên phúc lợi</th>
                <th>Thành Tiền(VNĐ)</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
                
              </tr>
            </thead>
            <tbody>
              <tr>
                <td style="font-weight: bold">I</td>
                <td style="font-weight: bold">Phúc Lợi Chung</td>
                <td></td>
                <td></td>
                <td  style="font-weight: bold"><el-button slot="reference" class="btn btn-success"
                      >Được Duyệt</el-button
                    ></td>
                <td></td>
              </tr>
              <tr v-for="(item, index) in listGeneral" :key="index">
                <td style="font-weight: bold">{{ index + 1 }}.</td>
                <td></td>
                <td>{{ item.name }}</td>
                <td>{{ formatCurrency(item.price) }}</td>
                <td></td>
                <td>
                  <el-popover
                    placement="right"
                    width="250"
                    trigger="hover"
                    :content="item.text"
                  >
                    <el-button slot="reference" class="btn btn-warning"
                      >Xem mô tả</el-button
                    >
                  </el-popover>
                </td>
              </tr>
              <tr>
                <td style="font-weight: bold">II</td>
                <td style="font-weight: bold">Phúc Lợi Cá Nhân Hóa</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr v-for="(item, index) in listRegister" :key="index">
                <td style="font-weight: bold">{{ index + 1 }}.</td>
                <td></td>
                <td>{{ item.welfare.name }}</td>
                <td>{{ formatCurrency(item.welfare.price) }}</td>
                <td>{{item.status}}</td>
                <td>
                  <el-popover
                    placement="right"
                    width="250"
                    trigger="hover"
                    :content="item.welfare.text"
                  >
                    <el-button slot="reference" class="btn btn-warning"
                      >Xem mô tả</el-button
                    >
                  </el-popover>
                </td>
                
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- <pre>{{ userName }}</pre>
    <pre>{{ idUser }}</pre> -->
    </div>
  </div>
</template>
  
  <script>
/* eslint-disable */
import WelfareApi from "@/service/phucLoiService";
let welfareApi = new WelfareApi();
export default {
  name: "PhucLoiHome",
  data() {
    return {
      listGeneral: [],
      listRegister: [],
      userName: "",
      idUser: "",
    };
  },
  methods: {
    formatCurrency(value) {
      return Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(value);
    },
    formatDate(timestamp) {
      const date = new Date(timestamp);
      let day = date.getDate();
      day = day < 10 ? `0${day}` : day;
      let month = date.getMonth() + 1;
      month = month < 10 ? `0${month}` : month;
      let year = date.getFullYear();
      return `${day}/${month}/${year}`;
    },
    async getIdUserName() {
      await welfareApi.findID(this.userName).then((res) => {
        // self.isLoaded = true;
        this.idUser = res.data;
        // console.log(res.data);
        // console.log(this.listGeneral);
        console.log(this.idUser);
      });
    },
    async getAllWelfare() {
      await welfareApi.getAllWelfareByUser(this.idUser).then((res) => {
        // self.isLoaded = true;
        this.listRegister = res.data;
        // gọi ở đâu
        // console.log(res.data);
        // console.log(this.listRegister);
      });
    },
    async getAllGeneralWelfares() {
      await welfareApi.getAllGeneralWelfare().then((res) => {
        // self.isLoaded = true;
        this.listGeneral = res.data;
        // console.log(res.data);
        // console.log(this.listGeneral);
      });
    },
  },

  async created() {
    if (localStorage.getItem("user")) {
      this.userName = JSON.parse(localStorage.getItem("user")).userName;
      console.log(this.userName);
      welfareApi.findID(this.userName).then((res) => {
        this.idUser = res.data;
        console.log(this.idUser);
        this.getAllGeneralWelfares();
        this.getAllWelfare(); //day
      });
    }
    console.log("before");
    console.log(this.listRegister);
  },
};
</script>
  
<style scoped>
.pl-main {
  display: flex;
  max-height: calc(100vh - 110px);
}
.pl-container {
  justify-content: center;
  display: flex;
  flex: 1;
}
.pl-content {
  overflow: auto;
  width: 1400px;
  border-radius: 20px;
  margin-top: 65px;
  margin-bottom: 20px;
  background: linear-gradient(90deg, white 100%, #e4c9ac 0%), #e3c1d3;
  display: flex;
  flex-direction: column;
  justify-content: start;
}

.pl-table__content {
  text-align: center;
  padding: 0 50px;
  overflow: auto;
  height: 100%;
  margin: 20px 0px;
}
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}
::-webkit-scrollbar-thumb {
  background: #bdbdbd;
}
.pl-table__content table {
  width: 100%;
  border-collapse: collapse;
}
.pl-table__content table tr {
  background: rgba(217, 217, 217, 0.13);
}
.pl-table__content table tr th {
  border-right: 1px solid #c7c7c7;
  padding: 14px;
}
.pl-table__content table tr td {
  padding: 20px;
  line-height: 30px;
  height: 30px;
}
.pl-table__content table thead tr {
  background-color: #d9d9d9;
  position: sticky;
  top: 0;
}
.pl-table__content table thead th {
  font-size: 14px;
  font-weight: 600;
}
.pl-table__content table tr td {
  border-right: 1px solid #c7c7c7;
}
.pl-table__content table tbody tr {
  border-bottom: 1px solid #c7c7c7;
}
.pl-table__content table tbody tr:hover {
  background-color: pink;
}
#title {
  text-align: center;
  font-size: 20px;
  font-weight: 600;
  font-family: "Poppins", sans-serif;
  color: red;
  margin-top: 20px;
}
</style>
  
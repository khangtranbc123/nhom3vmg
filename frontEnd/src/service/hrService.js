import axios from 'axios';

/**
 * Api lấy ra danh sách toàn bộ nhân viên
 * */
class StaffApi {

    async getAllStaff() {
        return await axios.get('http://localhost:8083/api/nhanvien/listnhanvien');
    }

    async deleteStaff(id) {
        return await axios.delete('http://localhost:8083/api/nhanvien/delete/' + id)
    }
    async updateStaff(id, object) {

        return await axios.put('http://localhost:8083/api/nhanvien/update/' + id, object)
    }
    async createStaff(object) {
        return await axios.post('http://localhost:8083/api/nhanvien/createnhanvien', object)//day khoan
    }
}
export default StaffApi
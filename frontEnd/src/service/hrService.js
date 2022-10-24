import axios from 'axios';

/**
 * Api lấy ra danh sách toàn bộ nhân viên
 * */
class StaffService {

    async getAll() {
        return await axios.get('http://localhost:8083/api/staffs')
    }
    async getWelfare(id) {
        return await axios.get(`http://localhost:8083/api/staff-show/${id}`);
    }
    async getGeneralWelfare() {
        return await axios.get('http://localhost:8083/api/general-welfanes');
    }
    async getDepartments() {
        return await axios.get('http://localhost:8083/api/departments');
    }

    async deleteStaff(id) {
        return await axios.delete('http://localhost:8083/api/staff/' + id)
    }
    async updateStaff(id, object) {

        return await axios.put('http://localhost:8083/api/staff/' + id, object)
    }
    async createStaff(staff) {
        return await axios.post('http://localhost:8083/api/staff', staff)
    }
    async getRegisterWelfare() {
        return await axios.get('http://localhost:8083/api/registers')
    }
    async SuccessRegisterWelfare(id) {
        return await axios.put(`http://localhost:8083/api/register/${id}`)
    }
    async DeleteRegisterWelfare(id) {
        return await axios.put(`http://localhost:8083/api/register-delete/${id}`)
    }
}
export default new StaffService()
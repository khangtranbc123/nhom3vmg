import axios from 'axios';

/**
 * Api lấy ra danh sách toàn bộ phúc lợi
 * */
class WelfareApi {

    async getAllWelfare() {
        return await axios.get('http://localhost:8083/api/welfares');
    }

    async deleteWelfare(id) {
        return await axios.delete('http://localhost:8083/api/phucloi/delete/' + id)
    }
    async updateWelfare(id, object) {

        return await axios.put('http://localhost:8083/api/phucloi/update/' + id, object)
    }
    async createWelfare(object) {
        return await axios.post('http://localhost:8083/api/phucloi/create', object)
    }
    async registerWelfare(object) {
        return await axios.post('http://localhost:8083/api/regis_welfare', object)
    }
}
export default WelfareApi;

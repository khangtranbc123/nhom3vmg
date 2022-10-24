import axios from 'axios';

/**
 * Api lấy ra danh sách toàn bộ phúc lợi
 * */
class WelfareApi {

    async getAllWelfare() {
        return await axios.get('http://localhost:8083/api/welfares');
    }

    async deleteWelfare(id) {
        return await axios.delete('http://localhost:8083/api/welfare/' + id)
    }
    async updateWelfare(id, object) {

        return await axios.put('http://localhost:8083/api/welfare/' + id, object)
    }
    async createWelfare(object) {
        return await axios.post('http://localhost:8083/api/welfare', object)
    }
    async registerWelfare(object) {
        return await axios.post('http://localhost:8083/api/regis_welfare', object)
    }
}
export default WelfareApi;

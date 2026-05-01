const base = {
    get() {
        return {
            url : "http://localhost:8080/springbooto2ehg/",
            name: "springbooto2ehg",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbooto2ehg/front/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "南京市雨花驾校管理信息系统"
        } 
    }
}
export default base

<template>
			<el-main class="index-main">
    		<bread-crumbs :title="title" class="bread-crumbs"></bread-crumbs>
		<router-view class="router-view"></router-view>
	</el-main>
</template>

<script>
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				menuList: [],
				role: "",
				currentIndex: -2,
				itemMenu: [],
				title: '',
			};
		},
		mounted() {
			let menus = menu.list();
			this.menuList = menus;
			this.role = this.$storage.get("role");
		},
		created() {
			this.init();
		},
		methods: {
			init(){
				this.$nextTick(()=>{
				})
			},
			menuHandler(menu) {
				this.$router.push({
					name: menu.tableName
				});
				this.title = menu.menu;
			},
			titleChange(index, menus) {
				this.currentIndex = index
				this.itemMenu = menus;
			},
			homeChange(index) {
				this.itemMenu = [];
				this.title = ""
				this.currentIndex = index
				this.$router.push({
					name: 'home'
				});
			},
			centerChange(index) {
				this.itemMenu = [{
					"buttons": ["新增", "查看", "修改", "删除"],
					"menu": "修改密码",
					"tableName": "updatePassword"
				}, {
					"buttons": ["新增", "查看", "修改", "删除"],
					"menu": "个人信息管理",
					"tableName": "center"
				}];
				this.title = ""
				this.currentIndex = index
				this.$router.push({
					name: 'home'
				});

			}
		}
	};
</script>
<style lang="scss" scoped>
	.index-main {
		padding: 0 !important;
		min-height: calc(100vh - 64px);
		background:
		  radial-gradient(1000px 400px at 10% -10%, rgba(110,168,255,.10), transparent 60%),
		  radial-gradient(800px 400px at 95% -20%, rgba(166,111,255,.08), transparent 60%),
		  linear-gradient(180deg, #f6f9ff 0%, #fbfaff 100%);
		box-sizing: border-box;
		overflow-x: hidden;
	}

	a { text-decoration: none; color: #6ea8ff; }
	a:hover { color: #a66fff; }

	.router-view {
		padding: 0;
		margin: 0;
		background: transparent;
		box-sizing: border-box;
	}

	.bread-crumbs {
		width: 100%;
		padding: 0 24px;
		margin-top: 0;
		box-sizing: border-box;
	}

	.detail-form-content {
	    background: transparent;
	}
</style>

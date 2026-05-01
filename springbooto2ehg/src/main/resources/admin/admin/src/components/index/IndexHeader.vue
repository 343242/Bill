<template>
	<div class="navbar">
		<div class="title-menu">
			<div class="brand-logo">
				<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 13l2-2 4 4 8-8 4 4"/><path d="M3 18h18"/></svg>
			</div>
			<div class="title-name">{{this.$project.projectName}}</div>
		</div>
		<div class="right-menu">
			<div class="user-info">
				<span class="user-avatar">{{ avatarText }}</span>
				<span class="user-role">{{this.$storage.get('role')}}</span>
				<span class="user-name">{{this.$storage.get('adminName')}}</span>
			</div>
			<div v-if="this.$storage.get('role')!='管理员'" class="logout ghost" @click="onIndexTap">
				<svg viewBox="0 0 24 24" width="14" height="14" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/></svg>
				<span>前台</span>
			</div>
			<div class="logout" @click="onLogout">
				<svg viewBox="0 0 24 24" width="14" height="14" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
				<span>退出登录</span>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
				user: {},
			};
		},
		computed: {
			avatarText() {
				const name = this.$storage.get('adminName') || ''
				return name ? name.charAt(0).toUpperCase() : 'A'
			}
		},
		mounted() {
			let sessionTable = this.$storage.get("sessionTable")
			this.$http({
				url: sessionTable + '/session',
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					this.user = data.data;
					this.$storage.set('userid',data.data.id);
				} else {
					let message = this.$message
					message.error(data.msg);
				}
			});
		},
		methods: {
			onLogout() {
				let storage = this.$storage
				let router = this.$router
				storage.clear()
				router.replace({
					name: "login"
				});
			},
      			onIndexTap(){
      				window.location.href = `${this.$base.indexUrl}`
    			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		height: 64px;
		line-height: 64px;
		width: 100%;
		padding: 0 28px;
		box-sizing: border-box;
		background: rgba(255, 255, 255, 0.85);
		backdrop-filter: blur(18px) saturate(160%);
		-webkit-backdrop-filter: blur(18px) saturate(160%);
		border-bottom: 1px solid rgba(230, 236, 250, 0.9);
		position: relative;
		z-index: 111;
		display: flex;
		align-items: center;
		justify-content: space-between;
		font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;

		.title-menu {
			display: flex;
			align-items: center;
			height: 100%;

			.brand-logo {
				width: 38px;
				height: 38px;
				border-radius: 11px;
				display: flex;
				align-items: center;
				justify-content: center;
				background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%);
				color: #fff;
				box-shadow: 0 6px 16px rgba(110, 130, 255, 0.32);
				margin-right: 12px;
			}

			.title-name {
				font-size: 18px;
				font-weight: 700;
				background: linear-gradient(135deg, #1f2a44 0%, #5a68a8 100%);
				-webkit-background-clip: text;
				background-clip: text;
				color: transparent;
				letter-spacing: 0.5px;
			}
		}

		.right-menu {
			height: 100%;
			display: flex;
			justify-content: flex-end;
			align-items: center;
			gap: 10px;

			.user-info {
				display: flex;
				align-items: center;
				gap: 10px;
				padding: 6px 14px 6px 6px;
				background: linear-gradient(135deg, #f5f8ff 0%, #f9f4ff 100%);
				border: 1px solid rgba(230, 236, 250, 0.8);
				border-radius: 999px;
				font-size: 13px;
				line-height: 1;
				margin-right: 6px;

				.user-avatar {
					width: 30px;
					height: 30px;
					border-radius: 50%;
					background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%);
					color: #fff;
					font-size: 13px;
					font-weight: 600;
					display: flex;
					align-items: center;
					justify-content: center;
					line-height: 1;
				}
				.user-role {
					color: #8a96b5;
					font-size: 12px;
				}
				.user-name {
					color: #2b3452;
					font-weight: 600;
				}
			}

			.logout {
				display: inline-flex;
				align-items: center;
				gap: 6px;
				height: 34px;
				padding: 0 14px;
				line-height: 1;
				border-radius: 10px;
				font-size: 13px;
				color: #fff;
				background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%);
				box-shadow: 0 6px 14px rgba(110, 130, 255, 0.28);
				cursor: pointer;
				transition: transform 0.2s ease, box-shadow 0.2s ease, filter 0.2s ease;

				&:hover {
					transform: translateY(-1px);
					box-shadow: 0 10px 20px rgba(110, 130, 255, 0.38);
					filter: brightness(1.05);
				}

				&.ghost {
					background: #fff;
					color: #6ea8ff;
					border: 1px solid #d8e2fa;
					box-shadow: 0 4px 10px rgba(110, 130, 255, 0.1);

					&:hover {
						background: #f5f8ff;
						border-color: #6ea8ff;
					}
				}
			}
		}
	}
</style>

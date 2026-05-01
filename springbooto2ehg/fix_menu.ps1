$resp = Invoke-RestMethod -Uri 'http://localhost:8080/springbooto2ehg/menu/list?limit=10&page=1'
$menuJson = $resp.data.list[0].menujson
$parsed = $menuJson | ConvertFrom-Json
Write-Host "Before fix:"
Write-Host "  xueyuan backMenu count:" $parsed[1].backMenu.Count
Write-Host "  xueyuan frontMenu count:" $parsed[1].frontMenu.Count
foreach ($item in $parsed[1].frontMenu) {
    Write-Host "    frontMenu:" $item.menu
}
foreach ($item in $parsed[1].backMenu) {
    Write-Host "    backMenu:" $item.menu
}

# Build the complete menujson with xueyuan having both backMenu and frontMenu
$xueyuanBackMenu = @(
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-person"; buttons = @("查看","修改"); menu = "个人信息"; menuJump = "列表"; tableName = "xueyuan" }
        )
        menu = "个人信息管理"
    },
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-video"; buttons = @("查看"); menu = "理论视频学习"; menuJump = "列表"; tableName = "shipin" },
            @{ appFrontIcon = "cuIcon-favor"; buttons = @("查看","删除"); menu = "视频互动管理"; menuJump = "列表"; tableName = "storeup" },
            @{ appFrontIcon = "cuIcon-copy"; buttons = @("查看"); menu = "练习题库"; menuJump = "列表"; tableName = "exampaper" },
            @{ appFrontIcon = "cuIcon-attentionfavor"; buttons = @("查看"); menu = "理论考试"; menuJump = "列表"; tableName = "examrecord" },
            @{ appFrontIcon = "cuIcon-brand"; buttons = @("查看"); menu = "错题本"; menuJump = "列表"; tableName = "cuotiban" }
        )
        menu = "理论学习考试"
    },
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-vipcard"; buttons = @("新增","查看","删除"); menu = "时段预约申请"; menuJump = "列表"; tableName = "yuyuelianche" },
            @{ appFrontIcon = "cuIcon-time"; buttons = @("查看"); menu = "预约记录查询"; menuJump = "列表"; tableName = "yuyuelianche" }
        )
        menu = "练车预约申请"
    }
)

$xueyuanFrontMenu = @(
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-person"; buttons = @("查看","修改"); menu = "个人信息"; menuJump = "列表"; tableName = "xueyuan" }
        )
        menu = "个人信息管理"
    },
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-video"; buttons = @("查看"); menu = "理论视频学习"; menuJump = "列表"; tableName = "shipin" },
            @{ appFrontIcon = "cuIcon-favor"; buttons = @("查看","删除"); menu = "视频互动管理"; menuJump = "列表"; tableName = "storeup" },
            @{ appFrontIcon = "cuIcon-copy"; buttons = @("查看"); menu = "练习题库"; menuJump = "列表"; tableName = "exampaper" },
            @{ appFrontIcon = "cuIcon-attentionfavor"; buttons = @("查看"); menu = "理论考试"; menuJump = "列表"; tableName = "examrecord" },
            @{ appFrontIcon = "cuIcon-brand"; buttons = @("查看"); menu = "错题本"; menuJump = "列表"; tableName = "cuotiban" }
        )
        menu = "理论学习考试"
    },
    @{
        child = @(
            @{ appFrontIcon = "cuIcon-vipcard"; buttons = @("新增","查看","删除"); menu = "时段预约申请"; menuJump = "列表"; tableName = "yuyuelianche" },
            @{ appFrontIcon = "cuIcon-time"; buttons = @("查看"); menu = "预约记录查询"; menuJump = "列表"; tableName = "yuyuelianche" }
        )
        menu = "练车预约申请"
    }
)

# Update xueyuan role
$parsed[1].backMenu = $xueyuanBackMenu
$parsed[1].frontMenu = $xueyuanFrontMenu

$newMenuJson = $parsed | ConvertTo-Json -Depth 10 -Compress

# Get the menu record ID
$menuId = $resp.data.list[0].id

# Update via API
$body = @{
    id = $menuId
    menujson = $newMenuJson
} | ConvertTo-Json -Depth 2

$headers = @{
    "Content-Type" = "application/json"
}

try {
    $updateResp = Invoke-RestMethod -Uri "http://localhost:8080/springbooto2ehg/menu/update" -Method Post -Body $body -Headers $headers
    Write-Host "`nUpdate result:"
    Write-Host ($updateResp | ConvertTo-Json)
} catch {
    Write-Host "Error updating menu:" $_.Exception.Message
}

# Verify
$resp2 = Invoke-RestMethod -Uri 'http://localhost:8080/springbooto2ehg/menu/list?limit=10&page=1'
$menuJson2 = $resp2.data.list[0].menujson
$parsed2 = $menuJson2 | ConvertFrom-Json
Write-Host "`nAfter fix:"
Write-Host "  xueyuan backMenu count:" $parsed2[1].backMenu.Count
Write-Host "  xueyuan frontMenu count:" $parsed2[1].frontMenu.Count
foreach ($item in $parsed2[1].frontMenu) {
    Write-Host "    frontMenu:" $item.menu
}
foreach ($item in $parsed2[1].backMenu) {
    Write-Host "    backMenu:" $item.menu
}

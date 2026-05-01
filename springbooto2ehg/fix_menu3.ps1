# Login as admin first
$loginBody = @{
    username = "abo"
    password = "abo"
} | ConvertTo-Json

$headers = @{
    "Content-Type" = "application/json"
}

$loginResp = Invoke-RestMethod -Uri "http://localhost:8080/springbooto2ehg/users/login" -Method Post -Body $loginBody -Headers $headers
$token = $loginResp.token
Write-Host "Token: $token"

# Read the menu JSON from file
$menuJson = Get-Content -Path "f:\javapro\bs\springbooto2ehg\menu_data.json" -Raw -Encoding UTF8

# Build the update request body
$updateBody = @{
    id = 1
    menujson = $menuJson
} | ConvertTo-Json -Depth 2

# Update via API with token
$authHeaders = @{
    "Content-Type" = "application/json; charset=utf-8"
    "Token" = $token
}

try {
    $updateResp = Invoke-RestMethod -Uri "http://localhost:8080/springbooto2ehg/menu/update" -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($updateBody)) -Headers $authHeaders
    Write-Host "Update result:"
    Write-Host ($updateResp | ConvertTo-Json)
} catch {
    Write-Host "Error:" $_.Exception.Message
}

# Verify
Start-Sleep -Seconds 1
$resp2 = Invoke-RestMethod -Uri 'http://localhost:8080/springbooto2ehg/menu/list?limit=10&page=1'
$menuJson2 = $resp2.data.list[0].menujson
$parsed2 = $menuJson2 | ConvertFrom-Json
Write-Host "`nAfter fix - xueyuan:"
Write-Host "  backMenu count:" $parsed2[1].backMenu.Count
Write-Host "  frontMenu count:" $parsed2[1].frontMenu.Count
foreach ($item in $parsed2[1].frontMenu) {
    Write-Host "    frontMenu:" $item.menu
}
foreach ($item in $parsed2[1].backMenu) {
    Write-Host "    backMenu:" $item.menu
}

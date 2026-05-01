# Read the menu JSON from file
$menuJson = Get-Content -Path "f:\javapro\bs\springbooto2ehg\menu_data.json" -Raw -Encoding UTF8

# Get current menu record ID
$resp = Invoke-RestMethod -Uri 'http://localhost:8080/springbooto2ehg/menu/list?limit=10&page=1'
$menuId = $resp.data.list[0].id

Write-Host "Menu ID: $menuId"

# Build the update request body
$updateBody = @{
    id = $menuId
    menujson = $menuJson
} | ConvertTo-Json -Depth 2

# Save update body for debugging
$updateBody | Out-File -FilePath "f:\javapro\bs\springbooto2ehg\update_body.json" -Encoding UTF8

# Update via API
$headers = @{
    "Content-Type" = "application/json; charset=utf-8"
}

try {
    $updateResp = Invoke-RestMethod -Uri "http://localhost:8080/springbooto2ehg/menu/update" -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($updateBody)) -Headers $headers
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

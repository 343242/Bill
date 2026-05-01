# Login as admin first
$loginBody = @{
    username = "abo"
    password = "abo"
} | ConvertTo-Json

$headers = @{
    "Content-Type" = "application/json"
}

$loginResp = Invoke-RestMethod -Uri "http://localhost:8080/springbooto2ehg/users/login" -Method Post -Body $loginBody -Headers $headers
Write-Host "Login response:"
Write-Host ($loginResp | ConvertTo-Json -Depth 5)

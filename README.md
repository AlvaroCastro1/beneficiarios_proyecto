# Lineas de codigo
git ls-files | Where-Object { $_ -like "*.java" } | ForEach-Object { Get-Content $_ } | Measure-Object -Line

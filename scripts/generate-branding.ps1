Add-Type -AssemblyName System.Drawing

function New-Canvas($w, $h, $bg) {
    $bmp = New-Object System.Drawing.Bitmap($w, $h)
    $g = [System.Drawing.Graphics]::FromImage($bmp)
    $g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
    $g.TextRenderingHint = [System.Drawing.Text.TextRenderingHint]::AntiAliasGridFit
    $g.Clear($bg)
    return @{ bmp = $bmp; g = $g }
}

function Draw-Monogram($g, $cx, $cy, $size, $fontSize, $color) {
    $font = New-Object System.Drawing.Font("Segoe UI", $fontSize, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
    $sf = New-Object System.Drawing.StringFormat
    $sf.Alignment = [System.Drawing.StringAlignment]::Center
    $sf.LineAlignment = [System.Drawing.StringAlignment]::Center
    $rect = New-Object System.Drawing.RectangleF(($cx - $size/2), ($cy - $size/2), $size, $size)
    $brush = New-Object System.Drawing.SolidBrush($color)
    $g.DrawString("M", $font, $brush, $rect, $sf)
    $font.Dispose(); $brush.Dispose(); $sf.Dispose()
}

function Save-Jpeg($bmp, $path, $quality) {
    $enc = [System.Drawing.Imaging.ImageCodecInfo]::GetImageEncoders() | Where-Object { $_.MimeType -eq "image/jpeg" }
    $ep = New-Object System.Drawing.Imaging.EncoderParameters(1)
    $ep.Param[0] = New-Object System.Drawing.Imaging.EncoderParameter([System.Drawing.Imaging.Encoder]::Quality, $quality)
    $bmp.Save($path, $enc, $ep)
}

$branding = "C:\Users\Eco Mike\Desktop\PERSONAL PROJECT\Portfolio\public\images\branding"
$profile  = "C:\Users\Eco Mike\Desktop\PERSONAL PROJECT\Portfolio\public\images\profile"
$projects = "C:\Users\Eco Mike\Desktop\PERSONAL PROJECT\Portfolio\public\images\projects"
New-Item -ItemType Directory -Force -Path $branding, $profile, $projects | Out-Null

$bgColor = [System.Drawing.Color]::FromArgb(255, 10, 15, 26)
$accent  = [System.Drawing.Color]::FromArgb(255, 16, 185, 129)
$textCol = [System.Drawing.Color]::FromArgb(255, 241, 245, 249)

# --- Favicon 32x32 (with rounded corners) ---
$c = New-Canvas 32 32 $bgColor
$g = $c.g
$path = New-Object System.Drawing.Drawing2D.GraphicsPath
$r = 7
$path.AddArc(1, 1, $r*2, $r*2, 180, 90)
$path.AddArc(32-1-$r*2, 1, $r*2, $r*2, 270, 90)
$path.AddArc(32-1-$r*2, 32-1-$r*2, $r*2, $r*2, 0, 90)
$path.AddArc(1, 32-1-$r*2, $r*2, $r*2, 90, 90)
$path.CloseFigure()
$g.FillPath((New-Object System.Drawing.SolidBrush($bgColor)), $path)
$pen = New-Object System.Drawing.Pen($accent, 2)
$g.DrawRectangle($pen, 2, 2, 27, 27)
$pen.Dispose()
Draw-Monogram $g 16 15 24 17 $accent
$c.bmp.Save("$branding\favicon.png", [System.Drawing.Imaging.ImageFormat]::Png)
$c.g.Dispose(); $c.bmp.Dispose()

# --- Apple touch icon 180x180 ---
$c = New-Canvas 180 180 $bgColor
$g = $c.g
$p2 = New-Object System.Drawing.Drawing2D.GraphicsPath
$rad = 40
$p2.AddArc(2, 2, $rad*2, $rad*2, 180, 90)
$p2.AddArc(178-$rad*2, 2, $rad*2, $rad*2, 270, 90)
$p2.AddArc(178-$rad*2, 178-$rad*2, $rad*2, $rad*2, 0, 90)
$p2.AddArc(2, 178-$rad*2, $rad*2, $rad*2, 90, 90)
$p2.CloseFigure()
$g.FillPath((New-Object System.Drawing.SolidBrush($bgColor)), $p2)
$pen2 = New-Object System.Drawing.Pen($accent, 8)
$g.DrawRectangle($pen2, 8, 8, 163, 163)
$pen2.Dispose()
Draw-Monogram $g 90 88 132 92 $accent
$c.bmp.Save("$branding\apple-touch-icon.png", [System.Drawing.Imaging.ImageFormat]::Png)
$c.g.Dispose(); $c.bmp.Dispose()

# --- Open Graph image 1200x630 ---
$c = New-Canvas 1200 630 $bgColor
$g = $c.g
$gridPen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(30, 148, 163, 184), 1)
for ($x = 0; $x -le 1200; $x += 60) { $g.DrawLine($gridPen, $x, 0, $x, 630) }
for ($y = 0; $y -le 630; $y += 60) { $g.DrawLine($gridPen, 0, $y, 1200, $y) }
$gridPen.Dispose()

$monoBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(110, 148, 163, 184))
$monoFont = New-Object System.Drawing.Font("Consolas", 20, [System.Drawing.FontStyle]::Regular, [System.Drawing.GraphicsUnit]::Pixel)
$g.DrawString("> morddy --engineer", $monoFont, $monoBrush, 90, 60)
$monoFont.Dispose(); $monoBrush.Dispose()

# monogram tile
$g.FillRectangle((New-Object System.Drawing.SolidBrush($bgColor)), 0, 0, 0, 0)
$tileBg = [System.Drawing.Color]::FromArgb(255, 15, 23, 42)
$g.FillEllipse((New-Object System.Drawing.SolidBrush($tileBg)), 88, 200, 250, 250)
$ringPen = New-Object System.Drawing.Pen($accent, 5)
$g.DrawEllipse($ringPen, 88, 200, 250, 250)
$ringPen.Dispose()
Draw-Monogram $g 213 325 180 130 $accent

# text
$titleFont = New-Object System.Drawing.Font("Segoe UI", 56, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$titleBrush = New-Object System.Drawing.SolidBrush($textCol)
$g.DrawString("Ifedayo Matthew", $titleFont, $titleBrush, 400, 215)
$titleFont.Dispose(); $titleBrush.Dispose()

$roleFont = New-Object System.Drawing.Font("Segoe UI", 30, [System.Drawing.FontStyle]::Regular, [System.Drawing.GraphicsUnit]::Pixel)
$roleBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 148, 163, 184))
$g.DrawString("Full-Stack Software Developer", $roleFont, $roleBrush, 400, 300)
$g.DrawString("Cybersecurity-Focused Engineer", $roleFont, $roleBrush, 400, 345)
$roleFont.Dispose(); $roleBrush.Dispose()

$accentFont = New-Object System.Drawing.Font("Consolas", 22, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$accentBrush = New-Object System.Drawing.SolidBrush($accent)
$g.DrawString("MORDDY", $accentFont, $accentBrush, 400, 430)
$g.DrawString("next.js . react . node.js . postgresql . aws . wazuh", $accentFont, $accentBrush, 400, 470)
$accentFont.Dispose(); $accentBrush.Dispose()

Save-Jpeg $c.bmp "$branding\og-image.jpg" 92
$c.g.Dispose(); $c.bmp.Dispose()

Write-Output "Branding assets generated."

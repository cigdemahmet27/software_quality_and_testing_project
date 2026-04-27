#!/bin/bash
# chmod +x super_clean_coverage.sh
# ./super_clean_coverage.sh
# open analysis/coverage_reports/llm1/base/index.html 
# open analysis/coverage_reports/llm1/improved/index.html 
# open analysis/coverage_reports/llm2/base/index.html 
# open analysis/coverage_reports/llm2/improved/index.html


# --- HAZIRLIK ---
echo "🚀 Operasyon başlıyor: Klasörler temizleniyor..."
rm -rf tmp analysis/coverage_reports
mkdir -p tmp
mkdir -p analysis/coverage_reports/llm1/base analysis/coverage_reports/llm1/improved
mkdir -p analysis/coverage_reports/llm2/base analysis/coverage_reports/llm2/improved

# Yolları kolaylık olsun diye değişkenlere atayalım
BASE_DIR="src/main/java/humaneval/base"
IMPROVED_DIR="src/main/java/humaneval/improved"

# ---------------------------------------------------------
# 1. BÖLÜM: LLM1 ANALİZİ
# ---------------------------------------------------------
echo "📦 LLM1 için saha hazırlanıyor (LLM2 tmp'ye taşınıyor)..."
mv $BASE_DIR/llm2 tmp/
mv $IMPROVED_DIR/llm2 tmp/

# LLM1 - BASE
echo "🧪 LLM1 Base raporu üretiliyor..."
mv $IMPROVED_DIR/llm1 tmp/ # Improved'ı da kaldır, sadece base kalsın
mvn clean test -Dtest="humaneval.base.llm1.**" jacoco:report
cp -r target/site/jacoco/* analysis/coverage_reports/llm1/base/

# LLM1 - IMPROVED
echo "🧪 LLM1 Improved raporu üretiliyor..."
mv $BASE_DIR/llm1 tmp/ # Base'i kaldır
mv tmp/llm1/improved $IMPROVED_DIR/ # Improved'ı geri koy
mvn clean test -Dtest="humaneval.improved.llm1.**" jacoco:report
cp -r target/site/jacoco/* analysis/coverage_reports/llm1/improved/

# LLM1 Temizliği: LLM1'leri komple tmp'ye al
mv $IMPROVED_DIR/llm1 tmp/

# ---------------------------------------------------------
# 2. BÖLÜM: LLM2 ANALİZİ
# ---------------------------------------------------------
echo "📦 LLM2 için saha hazırlanıyor (LLM1 zaten tmp'de)..."
mv tmp/llm2/base $BASE_DIR/

# LLM2 - BASE
echo "🧪 LLM2 Base raporu üretiliyor..."
mvn clean test -Dtest="humaneval.base.llm2.**" jacoco:report
cp -r target/site/jacoco/* analysis/coverage_reports/llm2/base/

# LLM2 - IMPROVED
echo "🧪 LLM2 Improved raporu üretiliyor..."
mv $BASE_DIR/llm2 tmp/ # Base'i kaldır
mv tmp/llm2/improved $IMPROVED_DIR/ # Improved'ı koy
mvn clean test -Dtest="humaneval.improved.llm2.**" jacoco:report
cp -r target/site/jacoco/* analysis/coverage_reports/llm2/improved/

# ---------------------------------------------------------
# 3. BÖLÜM: GERİ TOPLAMA
# ---------------------------------------------------------
echo "🧹 Saha temizleniyor, dosyalar yerlerine iade ediliyor..."
# Her şeyi geri taşı (hata almamak için varlarsa taşı)
mv tmp/llm1/base $BASE_DIR/ 2>/dev/null
mv tmp/llm1/improved $IMPROVED_DIR/ 2>/dev/null
mv tmp/llm2/base $BASE_DIR/ 2>/dev/null
mv tmp/llm2/improved $IMPROVED_DIR/ 2>/dev/null

rm -rf tmp

echo "✅ İşlem tamam! analysis/coverage_reports klasörüne bakabilirsin."
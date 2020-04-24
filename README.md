# ExcelUtil
用于导入导出Excel的Util包，基于Java的POI。可将List&lt;Bean>导出成Excel，或读取Excel成List&lt;Bean>,读取时有验证和Log，
支持Excel模版导出，以及导入导出Bean字段选择是否忽略
本ExcelUtil是基于SargerasWang/ExcelUtil（https://github.com/SargerasWang/ExcelUtil）项目所在的二次版本开发升级
***
# 使用方法
数据模型
通常可以使用你的数据表bean,当然如果需要导入/导出的字段与数据表差异很大,可以新增bean,当然也可以跳过bean,直接使用Map,下面是示例Bean:
```Java  
  
	public class Model {
    @ExcelCell(index = 0)
    private String a;
    @ExcelCell(index = 1, ignoreExport = true)
    private String b;
    @ExcelCell(index = 2, ignoreImport = true)
    private String c;
    @ExcelCell(index = 4, ignore = true)
    private Date d;

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
    
    public Model() {
    	
    }
    
    //setter and getter 省略...
  
```

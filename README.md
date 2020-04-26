# ExcelUtil
用于导入导出Excel的Util包，基于Java的POI。可将List&lt;Bean>导出成Excel，或读取Excel成List&lt;Bean>,读取时有验证和Log，
支持Excel模版导出，以及导入导出Bean字段可选择是否忽略
  
本ExcelUtil是基于[SargerasWang/ExcelUtil](https://github.com/SargerasWang/ExcelUtil)项目进行的二次版本开发升级使其
支持Excel模版导出，以及导入导出Bean字段可选择是否忽略
***
# 使用方法
**ExcelCell注解说明**
* index 顺序(不是指在Excel中的第几列)
* ignore 字段在导入导出时忽略，默认为false
* ignoreImport 字段在导入时忽略，默认为false
* ignoreExport 字段在导出时忽略，默认为false
* defaultValue 当值为null时要显示的值 default StringUtils.EMPTY 

**Valid注解说明**  
用户单元格校验
* in 必须与in中String相符,目前仅支持String类型 e.g. {"key","value"}
* allowNull 是否允许为空,用于验证数据 默认值 true
* gt 大于
* lt 小于
* ge 大于等于
* le 小于等于

 **数据模型**  
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
 **导出-Bean方式**  
```Java  
 public void exportXls() throws IOException {
        //用排序的Map且Map的键应与ExcelCell注解的index对应
        Map<String,String> map = new LinkedHashMap<>();
        map.put("a","姓名");
        map.put("b","年龄");
        map.put("c","性别");
        map.put("d","出生日期");
        Collection<Object> dataset=new ArrayList<Object>();
        dataset.add(new Model("", "", "",null));
        dataset.add(new Model(null, null, null,null));
        dataset.add(new Model("李四", "24", "女",new Date()));
        dataset.add(new Model("王五", "34", "男",new Date()));
        File f=new File("test.xlsx");
        OutputStream out =new FileOutputStream(f);
        
        ExcelUtil.exportExcel(map, dataset, out, ExcelType.XLXS);
        out.close();
    }
 ```
 **导出-Map方式**
 ```Java 
   public void exportXls() throws IOException {
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> map =new LinkedHashMap<>();
    map.put("name", "");
    map.put("age", "");
    map.put("birthday","");
    map.put("sex","");
    Map<String,Object> map2 =new LinkedHashMap<String, Object>();
    map2.put("name", "测试是否是中文长度不能自动宽度.测试是否是中文长度不能自动宽度.");
    map2.put("age", null);
    map2.put("sex", null);
    map.put("birthday",null);
    Map<String,Object> map3 =new LinkedHashMap<String, Object>();
    map3.put("name", "张三");
    map3.put("age", 12);
    map3.put("sex", "男");
    map3.put("birthday",new Date());
    list.add(map);
    list.add(map2);
    list.add(map3);
    Map<String,String> map1 = new LinkedHashMap<>();
    map1.put("name","姓名");
    map1.put("age","年龄");
    map1.put("birthday","出生日期");
    map1.put("sex","性别");
    File f= new File("test.xls");
    OutputStream out = new FileOutputStream(f);
    ExcelUtil.exportExcel(map1,list, out, ExcelType.XLX );
    out.close();
  }
  ```
  - - - -
  **导入**  
  ExcelUtil.importExcel 方法的第一个参数,是个Class,如果你有Bean,就写Bean的Class,反之,直接Map.class  
  **XLS 文件**
   ```Java
    public void importXls() throws FileNotFoundException {
    File f=new File("src/test/resources/test.xls");
    InputStream inputStream= new FileInputStream(f);
    
    ExcelLogs logs =new ExcelLogs();
    Collection<Model> importExcel = ExcelUtil.importExcel(Model.class, inputStream, "yyyy/MM/dd", logs , 0);
    
    for(Model m : importExcel){
      System.out.println(m.getA() + "  " + m.getB()+ "  " 
      +m.getC() + "  " + m.getD());
      
    }
``` 
    
  **XLSX 文件**
 ```Java
    public void importXlsx() throws FileNotFoundException {
    File f=new File("src/test/resources/test.xlsx");
    InputStream inputStream= new FileInputStream(f);

    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

    for(Map m : importExcel){
      System.out.println(m);
    }
  }
   ```
---
 **Execl 模版导出**
 **代码**
  ```Java
	  public void exportTemplate() throws Exception {
		 Collection<Object> dataset=new ArrayList<Object>();
	     dataset.add(new Model("张三", 44, "男",new Date()));
	     dataset.add(new Model("李四", 24, "女",new Date()));
	     dataset.add(new Model("王五", 34, "男",new Date()));
	     dataset.add(new Model("陆六", 23, "女",new Date()));
	     
		 File tempfile =new File("src/test/resources/testTemplate.xlsx");
		 InputStream is= new FileInputStream(tempfile);
		 File outfile= new File("testByTemp.xlsx");
		 OutputStream out = new FileOutputStream(outfile);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("model", dataset);
		 ExcelUtil.exportExcelByTemplate(is,map,out);
	 }

``` 
 Excel模版  
![image](https://github.com/Sky154/ExcelUtil/blob/master/image/temp.png)  
导出结果  
![image](https://github.com/Sky154/ExcelUtil/blob/master/image/export.png)

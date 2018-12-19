# sth_useful
## 关于ExcelUtil
> 主要使用Apache POI进行Excel的导入、导出
### 使用

#### 读取Excel中的数据
原始数据如下：<br>
![待读取数据.png](https://upload-images.jianshu.io/upload_images/309348-dbe77e133ff3b9f1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

方法：`public static List<List<String>> readFile(InputStream ins, int headRowNum) throws Exception`<br>
使用方式：
```java
        String filePath = "excel文件路径";
        File file = new File(filePath);
        InputStream ins = new FileInputStream(file);
        List<List<String>> lists = ExcelUtil.readFile(ins,  2);
        System.out.println(lists);
```
返回结果：<br>
`[[序号, 部门, 姓名, 上岗证号, 岗职, 入职时间], [, , , , , ], [1, 财务部, 赵六, 001, 出纳, 1540915200000], [1, 财务部, 张三, 002, 会计, 1517328000000]]`
注：对于Date类型，读取时读的是long类型参数

#### 将Excel中的数据转换为对应的实体Entity
* `public static <T> List<T> getListFromExcel(InputStream ins, int headRowNum, Class<T> cls, int parseIndex, String... excludeAttr) throws Exception`
* `public static <T> List<T> getListFromExcel(MultipartFile file, int headRowNum, Class<T> cls, int parseIndex, String... excludeAttr) throws Exception`
<br>两种方法本质上没有什么区别，可变参数excludeAttr配置实体不匹配的属性，例如：id
注：转换的时候，需要保证excel中属性的顺序与实体中属性的顺序对应，例如excel中部门-姓名-上岗证号...这样，则实体也应该按照这样的顺序定义属性。

> 此方法通常用于获取对应的excel数据，并批量插入数据库中。

#### 导出Excel
##### 根据List数据导出excel
*  `public static void exportExcel(String title, String[] headers, List<?> list, HttpServletResponse response, boolean useXSSF, String sheetName, List<String> includeAttr) `
<br>title:导出名字
    headers：表头数组，list:数据， useXSSF：是否使用2007Excel, sheetName：创建sheet名字，includeAttr：展示实体的哪些属性
   
   > ` public static void exportExcel(String title, String[] headers, List<?> list, HttpServletResponse response, boolean useXSSF, String sheetName, String... excludeAttr)`主要差别在最后一个，不展示哪些属性
   
   <br>
   如果在导出时需要合并单元格，先调用`public static void setMergeAreaList(List<RectangleArea> list)`
   例如：<br>
```java
   List<ExcelUtil.RectangleArea> list = new ArrayList<>();
   ExcelUtil.RectangleArea area = new ExcelUtil.RectangleArea(2,2,0,4);
   list.add(area);
   ExcelUtil.setMergeAreaList(list);
```
##### 根据字符串列表导出excel
`public static void exportExcel(String title, List<List<String>> list, boolean useXSSF, HttpServletResponse response)`
#### 根据字符串列表自定义导出实现
调用
`public static Workbook getExcelWorkBook(List<List<String>> list, boolean useXSSF)`获取workbook，
`public static void addMergeArea(Workbook workbook, int sheetIndex, List<RectangleArea> areas)`添加合并单元格数据，然后workboo.write(输出流)即可。
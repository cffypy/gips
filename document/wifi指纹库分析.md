原数据用json格式保存。可能有两个问题：

- ​	数据文件分散，数据量大时，不利于管理。
- ​	数据查询的效率比数据库低，影响算法效率。

因此，设计使用geopackage数据库。

# 一个楼栋一个数据库文件，比如shilintong.db。

## 	楼栋信息表:

### 		表名：building_info

#### 			字段：

##### 				ID：主键

##### 				name:楼栋名称，字符串

##### 				buildingID:楼栋标识，字符串

##### 				floors:楼层数，浮点数

##### 				boundingBox:地理范围，geometry

## 	楼层信息表：

### 		表名：floor_info

#### 				字段：

##### 						ID：主键

##### 						floorID:楼层标识，浮点数

##### 						buildingID:楼栋的ID，外键

##### 						MACNum:wifi mac地址数，整数

## 	wifi信息表

### 			表名：wifi_info

#### 					字段：

##### 							ID：主键

##### 							MacAddress:mac地址（BSSID），字符串

##### 							SSID：WIFI名称





​				
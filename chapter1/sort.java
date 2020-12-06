Page<User> findByLastname(String lastname, Pageable pageable);//根据分页参数查询User，返回一个带分页结果的Page(下一课时详解)对象（方法一）

Slice<User> findByLastname(String lastname, Pageable pageable);//我们根据分页参数返回一个Slice的user结果（方法二）

List<User> findByLastname(String lastname, Sort sort);//根据排序结果返回一个List（方法三）

List<User> findByLastname(String lastname, Pageable pageable);//根据分页参数返回一个List对象（方法四）

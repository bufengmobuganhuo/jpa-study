public interface ExpressionParser {

   // 根据传入的表达式生成Expression

   Expression parseExpression(String expressionString) throws ParseException;

   // 根据传入的表达式和ParserContext生成Expression对象

   Expression parseExpression(String expressionString, ParserContext context) throws ParseException;

}

package dev.dokan.dokan_java;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;


/**
 * This annotation is used used to indicate that a value with an integer type or an integer type pointer refers to an <b>unsigned value.</b><br>
 * In this case integers are <i>numbers without positions after decimal point.</i>
 * (Java's Default integer types being {@code byte, short, int, long} and their corresponding wrappers.)
 * <h1>Introduction</h1>
 * Java stores integer types in <a href="https://en.wikipedia.org/wiki/Two%27s_complement">Two's complement-Representation.</a>
 * Usually numbers represented as Two's complement use the Most-Significant-Bit (MSB) to store the sign of the number.
 * If a field is annotated with <i>@Unsigned</i> this rule <b>does not apply!</b>
 * Instead the MSB should be considered as part of the number itself, resulting in a doubled storage capacity of the field
 * while <b>removing</b> the support for signed values (the field becomes unsigned and the value should only be interpreted as positive.)
 * See this table as reference for the resulting differences when interpreting numbers:
 * <table>
 * <thead>
 *   <tr>
 *     <th>Signed value (Java Default)</th>
 *     <th>Unsigned values (@Unsigned)</th>
 *     <th>Bitmask (for a byte)</th>
 *   </tr>
 * </thead>
 * <tbody>
 *   <tr>
 *     <td>0</td>
 *     <td>0</td>
 *     <td>0000 0000</td>
 *   </tr>
 *   <tr>
 *     <td>1</td>
 *     <td>1</td>
 *     <td>0000 0001</td>
 *   </tr>
 *   <tr>
 *     <td>2</td>
 *     <td>2</td>
 *     <td>0000 0010</td>
 *   </tr>
 *   <tr>
 *     <td>64</td>
 *     <td>64</td>
 *     <td>0100 0000</td>
 *   </tr>
 *   <tr>
 *     <td>-128</td>
 *     <td>128</td>
 *     <td>1000 0000</td>
 *   </tr>
 *   <tr>
 *     <td>-127</td>
 *     <td>129</td>
 *     <td>1000 0001</td>
 *   </tr>
 *   <tr>
 *     <td>-1</td>
 *     <td>255</td>
 *     <td>1111 1111</td>
 *   </tr>
 * </tbody>
 * </table>
 * <h1>Usage</h1>
 * As Java only supports signed numbers, the correct interpretation and handling of unsigned numbers must be dealt with by the developer.
 * Because of this it's <b>strongly recommended</b> to tag all values that are unsigned as such <b>by using this annotation.</b><br>
 * <i>@Unsigned</i> is defined to {@link Target target} {@link java.lang.annotation.ElementType#TYPE_PARAMETER} and
 * {@link java.lang.annotation.ElementType#TYPE_USE} and can therefore be applied to all usages of types (type contexts)
 * and parameterized types respectively. See <a href="https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.6.4.1">&sect9.6.4.1</a>
 * and <a href="https://docs.oracle.com/javase/specs/jls/se11/html/jls-4.html#jls-4.11">&sect4.11</a> of "The Java&reg Language Specification" for
 * further reference.<br>
 * <br>
 * At least the following cases ("minimum usage") should be annotated with <i>@Unsigned</i> to
 * guarantee the best quality of code:
 * <ul>
 *     <li>Field declarations: {@code @Unsigned private final int index;}
 *     <li>Local variable declarations: {@code @Unsigned int i;}
 *     <li>Parameters: {@code public void remove(@Unsigned int index) {...}}
 *     <li>Method return types: {@code public @Unsigned int getIndex() {...}}
 * </ul><br>
 * Additionally it's recommended to annotate <b>any</b> other usage of unsigned types ("advanced usage"),
 * especially (but not limited to):
 * <ul>
 *     <li>Parameterized types: {@code List<@Unsigned Integer> indices = new ArrayList<>();}
 *     <li>Casts: {@code Integer i = (@Unsigned Integer) uInt;}
 *     <li>Type declarations: {@code public class @Unsigned UInt {...}}
 *     <li>Extension/Implementation of unsigned types: {@code public class //@Unsigned// Index extends @Unsigned UInt {...}}
 * </ul>
 * <b>Note:</b> Contributions to the dokan-java project must annotate all usages of unsigned types (Minimum usage <b>and</b> advanced usage)
 * with <i>@Unsigned.</i>
 * <h1>Pitfalls</h1>
 * If a field is annotated with <i>@Unsigned</i> developers should take extra care handling it as some unsafe operations may lead
 * to unexpected results.
 * An operation is considered <i>safe</i> if using it with unsigned values yields the same results as using it with signed numbers.
 * ("An operation is safe if it can be used the same way when dealing with unsigned values as one would use it with signed numbers.")
 * The following (inconclusive) table shows which operations are safe or unsafe and how operations can be dealt with alternatively.
 * <table>
 * <thead>
 *   <tr>
 *     <th>Operation</th>
 *     <th>Safe/Unsafe</th>
 *     <th>Recommended course of action</th>
 *   </tr>
 * </thead>
 * <tbody>
 *   <tr>
 *     <td>Adding (+)</td>
 *     <td>Safe</td>
 *     <td></td>
 *   </tr>
 *   <tr>
 *     <td>Subtracting (-)</td>
 *     <td>Safe</td>
 *     <td></td>
 *   </tr>
 *   <tr>
 *     <td>Multiplication (*)</td>
 *     <td>Safe</td>
 *     <td></td>
 *   </tr>
 *   <tr>
 *     <td>Division (/)</td>
 *     <td>Unsafe</td>
 *     <td>{@link UnsignedNumbers#divideUnsigned(int, int)}</td>
 *   </tr>
 *   <tr>
 *     <td>Remainder/Modulo</td>
 *     <td>Unsafe</td>
 *     <td>{@link UnsignedNumbers#remainderUnsigned(int, int)}</td>
 *   </tr>
 *   <tr>
 *     <td>Comparision (%)</td>
 *     <td>Unsafe</td>
 *     <td>{@link UnsignedNumbers#compareUnsigned(int, int)}</td>
 *   </tr>
 *   <tr>
 *     <td>Printing</td>
 *     <td>Unsafe</td>
 *     <td>{@link UnsignedNumbers#toUnsignedString(int)}</td>
 *   </tr>
 *   <tr>
 *     <td>Downcasting</td>
 *     <td>Safe</td>
 *     <td>{@link UnsignedNumbers#toUnsignedInt(long)}</td>
 *   </tr>
 *   <tr>
 *     <td>Upcasting</td>
 *     <td>Unsafe</td>
 *     <td>{@link UnsignedNumbers#toUnsignedInt(short)}</td>
 *   </tr>
 * </tbody>
 * </table>
 * <b>Note:</b> Operations that require two numbers (e.g. addition, subtraction) should <b>never</b> be used with a signed
 * and an unsigned number as arguments, even if the operation is usually considered safe.
 * Doing so can result in heavy computational errors, as seen here:<br>
 * <br>
 * {@code //Don't do this:}<br>
 * {@code byte a = 64; //0100 0000}<br>
 * {@code @Unsigned byte b = 64; //0100 0000}<br>
 * {@code byte c = a + b; //0100 0000 + 0100 0000}<br>
 * {@code //--> c = -128; //1000 0000}<br>
 * <br>
 * This is a problem for the following reason: Before the addition the status (signed/unsigned) of
 * <i>a</i> and <i>b</i> didn't matter (in fact they were equal). As soon as the value exceeds 127 the developer needs to decide
 * whether <i>c</i> is signed or unsigned.<br>
 * If <i>c</i> is unsigned all future users of <i>c</i> must take care that they
 * interpret it correctly. Also <i>a</i> comes from a signed context and could be negative.
 * If <i>a's</i> MSB is set to indicate a negative value any computation that considers <i>a</i> unsigned must be wrong
 * as it would interpret <i>a</i> as a big positive number instead of a negative number.<br>
 * If <i>c</i> is interpreted as signed, the computation is plain wrong (64 + 64 is not -128) because it leads to a number-overflow.<br>
 * <br>
 * {@code //Instead do this:}<br>
 * {@code byte a = 64;}<br>
 * {@code @Unsigned byte b = 64;}<br>
 * {@code short s = UnsignedNumbers.toUnsignedShort(b);}<br>
 * {@code short c = s + a;}<br>
 * {@code //--> c = 128;}<br>
 *
 * @author <a href="https://github.com/JaniruTEC">JaniruTEC</a>
 * @see UnsignedNumbers
 * @since 2.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
//In Theory TYPE_USE should contain TYPE_PARAMETER,
//but the documentation is so vague that I'm really not sure to be honest
@Target(value = {TYPE_PARAMETER, TYPE_USE})
public @interface Unsigned {

}

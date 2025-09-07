

package apisustentavel.fullstack.validation.validators;

import apisustentavel.fullstack.validation.annotations.ValidCNPJ;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<ValidCNPJ, String>
{

    @Override
    public void initialize(ValidCNPJ constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context)
    {
        if (cnpj == null || cnpj.isEmpty())
        {
            return false;
        }

        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se tem 14 dígitos
        if (cnpj.length() != 14)
        {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 00.000.000/0000-00)
        if (cnpj.matches("(\\d)\\1{13}"))
        {
            return false;
        }

        // Validação do CNPJ
        try
        {
            int[] digits = new int[14];
            for (int i = 0; i < 14; i++)
            {
                digits[i] = Integer.parseInt(cnpj.substring(i, i + 1));
            }

            // Calcula o primeiro dígito verificador
            int sum = 0;
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 12; i++)
            {
                sum += digits[i] * weights1[i];
            }
            int remainder = sum % 11;
            int digit1 = (remainder < 2) ? 0 : 11 - remainder;

            // Calcula o segundo dígito verificador
            sum = 0;
            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 13; i++)
            {
                sum += digits[i] * weights2[i];
            }
            remainder = sum % 11;
            int digit2 = (remainder < 2) ? 0 : 11 - remainder;

            // Verifica se os dígitos calculados conferem com os informados
            return digit1 == digits[12] && digit2 == digits[13];
        }catch (NumberFormatException e)
        {
            return false;
        }
    }
}


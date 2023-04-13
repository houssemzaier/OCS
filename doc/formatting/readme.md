# formatting git hook

1 - write this file in the `.git/hooks/pre-commit`

```
#!/bin/sh

# Run ktlintCheck using the Gradle wrapper
./gradlew ktlintCheck --daemon
status=$? # Store the exit status (integer value) in the variable 'status'

if [ "$status" -eq 0 ]; then
# The command succeeded (exit status is 0)
echo "Code formatting is correct. Proceeding with the commit."
exit 0
else
# The command failed (exit status is non-zero)
echo "Code formatting issues detected. Please fix them before committing."
exit 1
fi
```

2 - let it be executable:

```
chmod +x .git/hooks/pre-commit
```

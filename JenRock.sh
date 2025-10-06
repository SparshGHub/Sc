#!/bin/bash

# Check if Jenkins service is running
if systemctl is-active --quiet jenkins; then
    echo "Jenkins service is already running."
else
    echo "Jenkins service is not running. Starting Jenkins..."
    sudo systemctl start jenkins.service
    # Verify if Jenkins started successfully
    if systemctl is-active --quiet jenkins; then
        echo "Jenkins service started successfully."
    else
        echo "Failed to start Jenkins service."
        exit 1
    fi
fi

# Check if ngrok is already running on port 8080
if pgrep -f "ngrok http 8080" > /dev/null; then
    echo "ngrok is already running on port 8080."
else
    echo "ngrok is not running. Starting ngrok on port 8080..."
    # Run ngrok in the background
    ngrok http 8080 &
    # Wait briefly to ensure ngrok starts
    sleep 2
    # Verify if ngrok is running
    if pgrep -f "ngrok http 8080" > /dev/null; then
        echo "ngrok started successfully on port 8080."
    else
        echo "Failed to start ngrok."
        exit 1
    fi
fi

exit 0
